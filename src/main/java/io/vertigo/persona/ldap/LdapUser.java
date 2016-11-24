package io.vertigo.persona.ldap;

public final class LdapUser {
	private String name;
	private String firstName;
	private String accountName;
	private String email;
	private String company;
	private String department;
	private String office;
	private String phone;
	private String title;
	private String manager;
	private String thumbnail;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(final String accountName) {
		this.accountName = accountName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(final String company) {
		this.company = company;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(final String department) {
		this.department = department;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(final String office) {
		this.office = office;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(final String manager) {
		this.manager = manager;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(final String thumbnail) {
		this.thumbnail = thumbnail;
	}

}
