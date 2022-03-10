import { useCallback, useState } from "react";

import TextInput from "../common/TextInput.js";

function Login({ controller, setToken }) {
  const [email, didChangeEmail] = useState("");
  const [password, didChangePassword] = useState("");
  const [submitting, setSubmitting] = useState(false);

  const didSubmit = useCallback(
    (event) => {
      if (submitting) return;

      setSubmitting(true);
      controller
        .login(email, password)
        .then((result) => {
          if (!result.ok) {
            alert(
              `Une erreur est survenue (${result.code}), veuillez essayer avec d'autres informations de connexion`
            );
          } else {
            setToken(result.token);
          }
        })
        .catch((error) => {
          alert(
            `Une erreur est survenue (${error}), veuillez essayer avec d'autres informations de connexion`
          );
        })
        .finally(() => {
          setSubmitting(false);
        });
      event.preventDefault();
    },
    [email, password, controller, setSubmitting, submitting, setToken]
  );

  return (
    <div>
      <h1>Webflix</h1>
      <form onSubmit={didSubmit}>
        <TextInput
          id="email"
          label="Courriel"
          value={email}
          didChangeValue={didChangeEmail}
        />
        <TextInput
          id="password"
          label="Mot de passe"
          value={password}
          didChangeValue={didChangePassword}
        />
        <input type="submit" disabled={submitting} value="Se connecter" />
      </form>
    </div>
  );
}

export default Login;
