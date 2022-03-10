const MoviesListController = ({ baseUrl, token }) => ({
  getMovies: async (query) => {
    const params = new URLSearchParams();
    const entries = [
      ["countries", query.countries],
      ["languages", query.languages],
      ["genres", query.genres],
      ["directors", query.directors],
      ["actors", query.actors],
    ];

    for (const [key, values] of entries) {
      for (const value of values.split("|")) {
        params.append(key, value);
      }
    }

    params.set("title", query.title);
    params.set("yearFrom", query.yearFrom);
    params.set("yearTo", query.yearTo);

    const response = await fetch(
      `${baseUrl}/movies?${params.toString()}`,
      {
        headers: { "Authorization": token }
      }
    );

    const content = await response.json();

    return content;
  },
});

export default MoviesListController;
