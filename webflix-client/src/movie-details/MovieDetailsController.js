const MovieDetailsController = ({ baseUrl, token }) => ({
  getMovie: async (id) => {
    const response = await fetch(`${baseUrl}/movies/${id}`, {
      headers: { Authorization: token },
    });

    const content = await response.json();

    return content;
  },
  rentMovie: async (id) => {
    const response = await fetch(`${baseUrl}/movies/${id}`, {
      method: "POST",
      headers: { Authorization: token },
    });

    const content = await response.json();

    return content;
  },
});

export default MovieDetailsController;
