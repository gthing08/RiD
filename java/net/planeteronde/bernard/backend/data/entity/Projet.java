package net.planeteronde.bernard.backend.data.entity;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
public class Projet extends AbstractEntity {

	public enum Filiale {
		BY_BAT_IdF,
		BY_BAT_CSO,
		BY_BAT_GO,
		BY_BAT_NE,
		BY_BAT_SE
	}

	public enum Client {
		Privé,
		Collectivité,
		Etat,
		Bailleur
	}

	public enum Metier {
		Bâtiment
	}

	public enum LoB {
		Logement,
		Tertiaire,
		Enseignement,
		Santé,
		Locaux_industriels,
		Hotellerie
	}

	public enum SubLoB {
		Travaux,
		Conception_réalisation,
		Conception_Réalisation_Maintenance,
		Marché_de_Partenariat,
		Marché_Public_Global_de_Performance
	}

	public enum TypeOuvrage {
		Logement_collectif_Residentiel,
		Logement_collectif_social,
		Tertiaire_Bureau,
		Tertiaire_Commerce,
		Université,
		Lycée,
		Collège,
		Primaire,
		Crèche,
		Maternelle,
		Sante_Hopital,
		Santé_Clinique,
		Santé_Maison_de_retraite,
		Santé_Maison_de_santé,
		Santé_Ephad,
		Locaux_industriels,
		Hôtellerie
	}

	

	
	@NotNull
	@Size(min = 1, max = 255)
	@Column(unique = true)
	private String nom;

	
	private Filiale filiale;

	// Real price * 100 as an int to avoid rounding errors
	@Min(0)
	@Max(100000)
	private int montant;
	
	private String descriptif;
	private String source;
	private Client client;
	private String architecte;
	private Metier metier;
	private LoB loB;
	private SubLoB subLoB;
	private TypeOuvrage typeOuvrage;
	private String montantMarche;
	private LocalDate dateSignature;
	private String delaiClient;
	private String delaiTechnique;
	private LocalDate dateOuverture;
	private LocalDate dateFinTravaux;
	private String directeurBUNom;
	private String directeurBUPrenom;
	private String directeurBUEmail;
	private String chargeAffaireNom;
	private String chargeAffairePrenom;
	private String chargeAffaireEmail;
	private String respConceptionNom;
	private String respConceptionPrenom;
	private String respConceptionEmail;
	private String respSAVNom;
	private String respSAVPrenom;
	private String respSAVEmail;

	

	
	public Projet() {
		// Empty constructor is needed by Spring Data / JPA
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Filiale getFiliale() {
		return filiale;
	}

	public void setFiliale(Filiale filiale) {
		this.filiale = filiale;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}


	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getArchitecte() {
		return architecte;
	}

	public void setArchitecte(String architecte) {
		this.architecte = architecte;
	}

	public Metier getMetier() {
		return metier;
	}

	public void setMetier(Metier metier) {
		this.metier = metier;
	}

	public LoB getLoB() {
		return loB;
	}

	public void setLoB(LoB loB) {
		this.loB = loB;
	}

	public SubLoB getSubLoB() {
		return subLoB;
	}

	public void setSubLoB(SubLoB subLoB) {
		this.subLoB = subLoB;
	}

	public TypeOuvrage getTypeOuvrage() {
		return typeOuvrage;
	}

	public void setTypeOuvrage(TypeOuvrage typeOuvrage) {
		this.typeOuvrage = typeOuvrage;
	}

	public String getMontantMarche() {
		return montantMarche;
	}

	public void setMontantMarche(String montantMarche) {
		this.montantMarche = montantMarche;
	}

	public LocalDate getDateSignature() {
		return dateSignature;
	}

	public void setDateSignature(LocalDate dateSignature) {
		this.dateSignature = dateSignature;
	}

	public String getDelaiClient() {
		return delaiClient;
	}

	public void setDelaiClient(String delaiClient) {
		this.delaiClient = delaiClient;
	}

	public String getDelaiTechnique() {
		return delaiTechnique;
	}

	public void setDelaiTechnique(String delaiTechnique) {
		this.delaiTechnique = delaiTechnique;
	}

	public LocalDate getDateOuverture() {
		return dateOuverture;
	}

	public void setDateOuverture(LocalDate dateOuverture) {
		this.dateOuverture = dateOuverture;
	}

	public LocalDate getDateFinTravaux() {
		return dateFinTravaux;
	}

	public void setDateFinTravaux(LocalDate dateFinTravaux) {
		this.dateFinTravaux = dateFinTravaux;
	}

	public String getDirecteurBUNom() {
		return directeurBUNom;
	}

	public void setDirecteurBUNom(String directeurBUNom) {
		this.directeurBUNom = directeurBUNom;
	}

	public String getDirecteurBUPrenom() {
		return directeurBUPrenom;
	}

	public void setDirecteurBUPrenom(String directeurBUPrenom) {
		this.directeurBUPrenom = directeurBUPrenom;
	}

	public String getDirecteurBUEmail() {
		return directeurBUEmail;
	}

	public void setDirecteurBUEmail(String directeurBUEmail) {
		this.directeurBUEmail = directeurBUEmail;
	}

	public String getChargeAffaireNom() {
		return chargeAffaireNom;
	}

	public void setChargeAffaireNom(String chargeAffaireNom) {
		this.chargeAffaireNom = chargeAffaireNom;
	}

	public String getChargeAffairePrenom() {
		return chargeAffairePrenom;
	}

	public void setChargeAffairePrenom(String chargeAffairePrenom) {
		this.chargeAffairePrenom = chargeAffairePrenom;
	}

	public String getChargeAffaireEmail() {
		return chargeAffaireEmail;
	}

	public void setChargeAffaireEmail(String chargeAffaireEmail) {
		this.chargeAffaireEmail = chargeAffaireEmail;
	}

	public String getRespConceptionNom() {
		return respConceptionNom;
	}

	public void setRespConceptionNom(String respConceptionNom) {
		this.respConceptionNom = respConceptionNom;
	}

	public String getRespConceptionPrenom() {
		return respConceptionPrenom;
	}

	public void setRespConceptionPrenom(String respConceptionPrenom) {
		this.respConceptionPrenom = respConceptionPrenom;
	}

	public String getRespConceptionEmail() {
		return respConceptionEmail;
	}

	public void setRespConceptionEmail(String respConceptionEmail) {
		this.respConceptionEmail = respConceptionEmail;
	}

	public String getRespSAVNom() {
		return respSAVNom;
	}

	public void setRespSAVNom(String respSAVNom) {
		this.respSAVNom = respSAVNom;
	}

	public String getRespSAVPrenom() {
		return respSAVPrenom;
	}

	public void setRespSAVPrenom(String respSAVPrenom) {
		this.respSAVPrenom = respSAVPrenom;
	}

	public String getRespSAVEmail() {
		return respSAVEmail;
	}

	public void setRespSAVEmail(String respSAVEmail) {
		this.respSAVEmail = respSAVEmail;
	}

	

	


}

