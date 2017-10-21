package pakuteam.bedlam_experiment_0_1;

import com.Parse.ParseUser;
public class SocialPerson
{
	public String id;
	public String name;
	private String pass;
	public String profileURL;
	public String avatarURL;
	public String email;

	public SocialPerson(){ }

	private SocialPerson(String id, String name, String profileURL, String avatarURL,
						String email, String pass)
	{
		id = this.id;
		name = this.name;
		profileURL = this.profileURL;
		avatarURL = this.avatarURL;
		email = this.email;
		pass = this.pass;
	}

	//Convert any type of user to ParseUser
	@Override
	public static ParseUser FromSocialToParse(SocialPerson sp)
	{
		ParseUser u = new ParseUser();
		u.setUserName(sp.name);
		u.setEmail(sp.email);
		u.setPassword(sp.pass);
		u.set("profile", sp.profileURL);
		u.set("avatar", sp.avatarURL);

		return u;
	}

	@Override
	public String toString()
	{
		return "" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                ", profileURL='" + profileURL + '\'' +
                ", email='" + email + '\'';
	}

	@Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialPerson that = (SocialPerson) o;

        if (avatarURL != null ? !avatarURL.equals(that.avatarURL) : that.avatarURL != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (profileURL != null ? !profileURL.equals(that.profileURL) : that.profileURL != null)
            return false;

        return true;
}
}
