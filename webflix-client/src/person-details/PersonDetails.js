import { useState, useEffect } from "react";

function PersonDetails({ id, controller }) {
  const [isLoading, setLoading] = useState(true);
  const [person, setPerson] = useState(null);

  useEffect(() => {
    setLoading(true);
    controller
      .getPerson(id)
      .then((person) => setPerson(person))
      .catch((error) => alert(error))
      .finally(setLoading(false));
  }, [id, controller, isLoading, setLoading, setPerson]);

  return <div>{person}</div>;
}

export default PersonDetails;
