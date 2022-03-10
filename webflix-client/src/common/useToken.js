import { useState, useCallback } from "react";

const storageKey = "webflixToken";

function getToken() {
  const token = localStorage.getItem(storageKey);
  return token ? JSON.parse(token) : null;
}

function setToken(token) {
  localStorage.setItem(storageKey, JSON.stringify(token));
}

function useToken() {
  const [stateToken, setStateToken] = useState(getToken());

  const setBothToken = useCallback(
    (token) => {
      setStateToken(token);
      setToken(token);
    },
    [setStateToken]
  );

  const deleteToken = useCallback(() => {
    localStorage.removeItem(storageKey);
    setStateToken(null);
  }, [setStateToken]);

  return { token: stateToken, setToken: setBothToken, deleteToken };
}

export default useToken;
