import { useCallback, useState } from "react";

import MoviesListSearchInput from "./MoviesListSearchInput";

function MoviesList({ controller, navigateToMovie }) {
  const [movies, setMovies] = useState([]);
  const [loading, setLoading] = useState(false);
  const didSubmitSearch = useCallback(
    (query) => {
      if (loading) return;
      setLoading(true);

      controller
        .getMovies(query)
        .then((moviesResponse) => {
          setMovies(
            moviesResponse.map((movie) => ({
              id: movie.id,
              text: `${movie.title} (${movie.year})`,
            }))
          );
        })
        .catch((error) => alert(error))
        .finally(() => setLoading(false));
    },
    [controller, setMovies, setLoading, loading]
  );

  const onMovieClick = useCallback(
    (id) => () => {
      navigateToMovie(id);
    },
    [navigateToMovie]
  );

  return (
    <div>
      <MoviesListSearchInput didSubmit={didSubmitSearch} />
      <div>
        {loading ? (
          <p>Chargement...</p>
        ) : movies.length === 0 ? (
          <p>Aucun résultat.</p>
        ) : (
          movies.map((movie) => (
            <div key={movie.id} onClick={onMovieClick(movie.id)}>
              <p>{movie.text}</p>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default MoviesList;
