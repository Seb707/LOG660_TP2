import { useState, useCallback } from "react";
import TextInput from "../common/TextInput.js";

function MoviesListSearchInput(props) {
  const [title, didSetTitle] = useState("");
  const [yearFrom, didSetYearFrom] = useState(0);
  const [yearTo, didSetYearTo] = useState(new Date().getFullYear());
  const [countries, didSetCountries] = useState("");
  const [languages, didSetLanguages] = useState("");
  const [directors, didSetDirectors] = useState("");
  const [actors, didSetActors] = useState("");

  const didSubmit = useCallback(() => {
    props.didSubmit({
      title,
      yearFrom,
      yearTo,
      countries,
      languages,
      directors,
      actors,
    });
  }, [props, title, yearFrom, yearTo, countries, languages, directors, actors]);

  return (
    <form onSubmit={didSubmit}>
      <TextInput
        id="title"
        label="Titre"
        value={title}
        didChangeValue={didSetTitle}
      />
      <TextInput
        id="yearFrom"
        label="Année minimum"
        value={yearFrom}
        didChangeValue={didSetYearFrom}
      />
      <TextInput
        id="yearTo"
        label="Année maximale"
        value={yearTo}
        didChangeValue={didSetYearTo}
      />
      <TextInput
        id="country"
        label="Pays de production"
        value={countries}
        didChangeValue={didSetCountries}
      />
      <TextInput
        id="language"
        label="Langue(s)"
        value={languages}
        didChangeValue={didSetLanguages}
      />
      <TextInput
        id="director"
        label="Réalisateur(s)"
        value={directors}
        didChangeValue={didSetDirectors}
      />
      <TextInput
        id="actor"
        label="Acteurs"
        value={actors}
        didChangeValue={didSetActors}
      />
    </form>
  );
}

export default MoviesListSearchInput;
