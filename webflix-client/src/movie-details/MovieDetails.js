import { useState, useEffect, useCallback } from "react";
import Field from "../common/Field";

function MovieDetails({ id, controller, navigateToPerson }) {
  const [loading, setLoading] = useState(true);
  const [movie, setMovie] = useState(null);

  useEffect(() => {
    setLoading(true);
    controller
      .getMovie(id)
      .then((movieDetails) => {
        setMovie(movieDetails);
      })
      .catch((error) => alert(error))
      .finally(() => setLoading(false));
  }, [id, controller, setMovie]);

  const didSelectPerson = useCallback(
    (id) => () => {
      navigateToPerson(id);
    },
    [navigateToPerson]
  );

  const rentMovie = useCallback(() => {
    setLoading(true);
    controller
      .rentMovie(id)
      .then((response) => {
        alert(response);
      })
      .catch((error) => alert(error))
      .finally(() => setLoading(false));
  }, [id, controller, setLoading]);

  if (loading) {
    return <p>Chargement...</p>;
  }

  return (
    <div>
      <div>
        <img src={movie.posterUrl} alt="poster" />
        <h1>{movie.title}</h1>
        <p>{movie.shortDescription}</p>
      </div>
      <button onClick={rentMovie}>Louer ce film</button>
      <Field key="Année" value={movie.year} />
      <Field key="Pays" value={movie.countries.join(", ")} />
      <Field key="Langue" value={movie.language} />
      <Field key="Durée (minutes)" value={movie.duration} />
      <Field key="Genre(s)" value={movie.genres.join(", ")} />
      <Field
        key="Réalisateur"
        onClick={didSelectPerson(movie.directorId)}
        value={movie.director}
      />
      <Field key="Scénaristes" value={movie.screenwriters.join(", ")} />
      {movie.actors.map((actor) => (
        <div onClick={didSelectPerson(actor.id)}>
          <p>{`${actor.name} en tant que ${actor.role}`}</p>
        </div>
      ))}
    </div>
  );
}

export default MovieDetails;
