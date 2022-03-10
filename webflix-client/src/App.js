import { useState, useCallback } from "react";

import useToken from "./common/useToken.js";
import Login from "./login/Login.js";
import LoginController from "./login/LoginController.js";
import MoviesList from "./movies-list/MoviesList.js";
import MoviesListController from "./movies-list/MoviesListController.js";
import MovieDetailsController from "./movie-details/MovieDetailsController";
import MovieDetails from "./movie-details/MovieDetails";
import PersonDetailsController from "./person-details/PersonDetailsController";
import PersonDetails from "./person-details/PersonDetails";

function App() {
  const baseUrl = "http://localhost:8080";
  const loginController = LoginController({ baseUrl });

  const { token, setToken, deleteToken } = useToken();
  const [movieId, setMovieId] = useState(null);
  const [personId, setPersonId] = useState(null);

  const goBack = useCallback(() => {
    if (personId !== null) {
      setPersonId(null);
    } else if (movieId !== null) {
      setMovieId(null);
    } else if (token !== null) {
      deleteToken();
    }
  }, [token, deleteToken, movieId, setMovieId, personId, setPersonId]);

  if (token) {
    if (personId !== null) {
      const personDetailsController = PersonDetailsController({
        baseUrl,
        token,
      });
      return (
        <div>
          <button onClick={goBack}>Retour</button>
          <PersonDetails id={personId} controller={personDetailsController} />
        </div>
      );
    }

    if (movieId !== null) {
      const movieDetailsController = MovieDetailsController({
        baseUrl,
        token,
      });
      return (
        <div>
          <button onClick={goBack}>Retour</button>
          <MovieDetails
            id={movieId}
            controller={movieDetailsController}
            navigateToPerson={setPersonId}
          />
        </div>
      );
    }

    const moviesListController = MoviesListController({ baseUrl, token });

    return (
      <div>
        <button onClick={goBack}>Déconnexion</button>
        <MoviesList
          controller={moviesListController}
          navigateToMovie={setMovieId}
        />
      </div>
    );
  }

  return <Login controller={loginController} setToken={setToken} />;
}

export default App;
