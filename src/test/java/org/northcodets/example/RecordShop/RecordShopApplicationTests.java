package org.northcodets.example.RecordShop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.northcodets.example.RecordShop.model.Album;
import org.northcodets.example.RecordShop.model.Genre;
import org.northcodets.example.RecordShop.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class RecordShopApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	AlbumService albumService;



	@Test
	public void getAllAlbums() throws Exception {
		List<Album> mockAlbums = Arrays.asList(
				new Album(1L, "Sam Cooke", "Ain't That Good News",
						LocalDate.of(1964, 12, 22), 20.0, 50, true, Genre.JAZZ),
				new Album(2L, "Sabrina Carpenter", "Short n' Sweet",
						LocalDate.of(2024, 8, 23), 50.0, 400, true, Genre.POP),
				new Album(3L, "Kendrick Lamar", "To Pimp a Butterfly",
						LocalDate.of(2015,3,15), 44.0, 570, true, Genre.HIPHOP),
				new Album(4L, "Ella Fitzgerald", "Ella Fitzgerald Sings the Cole Porter Song Book",
						LocalDate.of(1956, 5, 15), 100.0, 50, true, Genre.JAZZ)
		);

		when(albumService.getAllAlbums()).thenReturn(mockAlbums);

		mockMvc.perform(get("/api/v1/albums"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].artist").value("Sam Cooke"))
				.andExpect(jsonPath("$[0].albumName").value("Ain't That Good News"))
				.andExpect(jsonPath("$[0].price").value(20.0))
				.andExpect(jsonPath("$[0].genre").value("JAZZ"))

				.andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].artist").value("Sabrina Carpenter"))
				.andExpect(jsonPath("$[1].albumName").value("Short n' Sweet"))
				.andExpect(jsonPath("$[1].price").value(50.0))
				.andExpect(jsonPath("$[1].genre").value("POP"))

				.andExpect(jsonPath("$[2].id").value(3))
				.andExpect(jsonPath("$[2].artist").value("Kendrick Lamar"))
				.andExpect(jsonPath("$[2].albumName").value("To Pimp a Butterfly"))
				.andExpect(jsonPath("$[2].price").value(44.0))
				.andExpect(jsonPath("$[2].genre").value("HIPHOP"))

				.andExpect(jsonPath("$[3].id").value(4))
				.andExpect(jsonPath("$[3].artist").value("Ella Fitzgerald"))
				.andExpect(jsonPath("$[3].albumName").value("Ella Fitzgerald Sings the Cole Porter Song Book"))
				.andExpect(jsonPath("$[3].price").value(100.0))
				.andExpect(jsonPath("$[3].genre").value("JAZZ"));

	}
	@Test
	public void getAlbumById() throws Exception {
		Album album = new Album(1L, "Sam Cooke", "Ain't That Good News",
				LocalDate.of(1964, 12, 22), 20.0, 50, true, Genre.JAZZ);

		when(albumService.getAlbumById(1L)).thenReturn(album);

		mockMvc.perform(get("/api/v1/albums/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.artist").value("Sam Cooke"))
				.andExpect(jsonPath("$.albumName").value("Ain't That Good News"));
	}
	@Test // testing for a 404 when no album is found
	public void getAlbumById_Invalid() throws Exception {

		when(albumService.getAlbumById(1L)).thenThrow(new RuntimeException("Album not found"));

		mockMvc.perform(get("/api/v1/albums/1"))
				.andExpect(status().isNotFound());
	}
@Test
@DisplayName("POST create new album")
	public void createNewAlbum() throws Exception {

		Album newAlbum = new Album(5L, "Tyler the Creator", "Flower Boy",
				LocalDate.of(2024, 8, 23), 25.0, 100, true, Genre.HIPHOP);

		when(albumService.createAlbum(any(Album.class))).thenReturn(newAlbum);

		mockMvc.perform(post("/api/v1/albums")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"artist\": \"Tyler the Creator\", \"albumName\": \"Flower Boy\", \"releaseDate\": \"2024-08-23\", \"price\": 25.0, \"stockCount\": 100, \"isInStock\": true, \"genre\": \"HIPHOP\" }"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.artist").value("Tyler the Creator"))
				.andExpect(jsonPath("$.albumName").value("Flower Boy"))
				.andExpect(jsonPath("$.price").value(25.0));
}
	@Test
	public void updateAlbum() throws Exception {
		Album albumToUpdate = new Album(1L, "Updated Artist", "Updated Album",
				LocalDate.of(2024, 8, 23), 30.0, 150, true, Genre.POP);

		when(albumService.updateAlbum(eq(1L), any(Album.class))).thenReturn(albumToUpdate);

		mockMvc.perform(put("/api/v1/albums/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(albumToUpdate)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.artist").value("Updated Artist"))
				.andExpect(jsonPath("$.albumName").value("Updated Album"))
				.andExpect(jsonPath("$.price").value(30.0))
				.andExpect(jsonPath("$.stockCount").value(150));
	}

	@Test
	public void deleteAlbumById() throws Exception {

		doNothing().when(albumService).deleteAlbum(1L);
		mockMvc.perform(delete("/api/v1/albums/1"))
				.andExpect(status().isNoContent());

		verify(albumService, times(1)).deleteAlbum(1L);
	}

	}



