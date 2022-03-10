package hibernateTest;

// default package
// Generated Mar. 10, 2022, 10:23:51 a.m. by Hibernate Tools 4.3.5.Final

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Films generated by hbm2java
 */
public class Films implements java.io.Serializable {

	private BigDecimal filmid;
	private Personnes personnes;
	private String titre;
	private BigDecimal anneedesortie;
	private String langue;
	private BigDecimal duree;
	private String resume;
	private String afficheurl;
	private Set bandeannonceses = new HashSet(0);
	private Set scenaristeses = new HashSet(0);
	private Set genreses = new HashSet(0);
	private Set copiefilmses = new HashSet(0);
	private Set roleacteurses = new HashSet(0);
	private Set paysdeproductions = new HashSet(0);

	public Films() {
	}

	public Films(BigDecimal filmid, String titre, BigDecimal anneedesortie, BigDecimal duree) {
		this.filmid = filmid;
		this.titre = titre;
		this.anneedesortie = anneedesortie;
		this.duree = duree;
	}

	public Films(BigDecimal filmid, Personnes personnes, String titre, BigDecimal anneedesortie, String langue,
			BigDecimal duree, String resume, String afficheurl, Set bandeannonceses, Set scenaristeses, Set genreses,
			Set copiefilmses, Set roleacteurses, Set paysdeproductions) {
		this.filmid = filmid;
		this.personnes = personnes;
		this.titre = titre;
		this.anneedesortie = anneedesortie;
		this.langue = langue;
		this.duree = duree;
		this.resume = resume;
		this.afficheurl = afficheurl;
		this.bandeannonceses = bandeannonceses;
		this.scenaristeses = scenaristeses;
		this.genreses = genreses;
		this.copiefilmses = copiefilmses;
		this.roleacteurses = roleacteurses;
		this.paysdeproductions = paysdeproductions;
	}

	public BigDecimal getFilmid() {
		return this.filmid;
	}

	public void setFilmid(BigDecimal filmid) {
		this.filmid = filmid;
	}

	public Personnes getPersonnes() {
		return this.personnes;
	}

	public void setPersonnes(Personnes personnes) {
		this.personnes = personnes;
	}

	public String getTitre() {
		return this.titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public BigDecimal getAnneedesortie() {
		return this.anneedesortie;
	}

	public void setAnneedesortie(BigDecimal anneedesortie) {
		this.anneedesortie = anneedesortie;
	}

	public String getLangue() {
		return this.langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public BigDecimal getDuree() {
		return this.duree;
	}

	public void setDuree(BigDecimal duree) {
		this.duree = duree;
	}

	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getAfficheurl() {
		return this.afficheurl;
	}

	public void setAfficheurl(String afficheurl) {
		this.afficheurl = afficheurl;
	}

	public Set getBandeannonceses() {
		return this.bandeannonceses;
	}

	public void setBandeannonceses(Set bandeannonceses) {
		this.bandeannonceses = bandeannonceses;
	}

	public Set getScenaristeses() {
		return this.scenaristeses;
	}

	public void setScenaristeses(Set scenaristeses) {
		this.scenaristeses = scenaristeses;
	}

	public Set getGenreses() {
		return this.genreses;
	}

	public void setGenreses(Set genreses) {
		this.genreses = genreses;
	}

	public Set getCopiefilmses() {
		return this.copiefilmses;
	}

	public void setCopiefilmses(Set copiefilmses) {
		this.copiefilmses = copiefilmses;
	}

	public Set getRoleacteurses() {
		return this.roleacteurses;
	}

	public void setRoleacteurses(Set roleacteurses) {
		this.roleacteurses = roleacteurses;
	}

	public Set getPaysdeproductions() {
		return this.paysdeproductions;
	}

	public void setPaysdeproductions(Set paysdeproductions) {
		this.paysdeproductions = paysdeproductions;
	}

}
