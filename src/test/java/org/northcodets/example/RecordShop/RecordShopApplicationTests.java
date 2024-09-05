package org.northcodets.example.RecordShop;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.northcodets.example.RecordShop.model.Album;
import org.northcodets.example.RecordShop.model.Genre;
import org.northcodets.example.RecordShop.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class RecordShopApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AlbumService albumService;



	@Test
	public void returnAllAlbums() throws Exception {
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

}
