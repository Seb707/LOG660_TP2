const LoginController = ({ baseUrl }) => ({
  login: async (email, password) => {
    const response = await fetch(`${baseUrl}/login`, {
      method: "POST",
      body: JSON.stringify({ email, password }),
    });
    if (response.ok) {
      return { ok: true, token: response.headers.get("Authorization") };
    } else {
      return { ok: false, code: response.code };
    }
  },
});

export default LoginController;
