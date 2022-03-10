const PersonDetailsController = ({ baseUrl, token }) => ({
  getPerson: async (id) => {
    const response = await fetch(`${baseUrl}/persons/${id}`, {
      headers: { Authorization: token },
    });

    const content = await response.json();

    return content;
  },
});

export default PersonDetailsController;
