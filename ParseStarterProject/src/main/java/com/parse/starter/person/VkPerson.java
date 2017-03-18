package pakuteam.bedlam_experiment_0_1;

import com.Parse.ParseUser;
import com.parse.starterperson.SocialPerson;

public class VkPerson extends SocialPerson
{

    /*** First name of social person*/
    public String firstName;
    /*** Last name of social person*/
    public String lastName;
    /*** Sex of social person.  1 - female, 2 - male, 0 - not presented. */
    public int sex;
    /*** Birthday of social person like dd.MM.yyyy or dd.mm if year not permitted*/
    public String birthday;
    /*** City of social person from user contacts*/
    public String city;
    /*** Country of social person from user contacts*/
    public String country;
    /*** Max sized image url of social person avatar*/
    public String photoMaxOrig;
    /*** Is social person online now. 1 - true, 0 - false*/
    public boolean online;
    /*** Username of social person*/
    public String username;
    /*** If social person has mobile. 1 - true, 0 - false*/
    public boolean hasMobile;
    /*** Mobile phone of social person from contacts*/
    public String mobilePhone;
    /*** Home phone of social person from contacts*/
    public String homePhone;
    /*** Year when social person was\will graduate*/
    public String status;
    /*** Is it permitted to post on social person wall*/
    public boolean canPost;
    /*** Is it permitted to see all posts from social person wall*/
    public boolean canSeeAllPosts;

	protected VkPerson()
	{
		lastName = this.lastName;
        firstName = this.firstName;
        sex = this.sex;
        birthday = this.birthday;
        city = this.city;
        country = this.county;
        photoMaxOrig = this.photoMaxOrig;
        online = this.online;
        username = this.username;
        hasMobile = this.hasMobile != 0x00;
        mobilePhone = this.mobilePhone;
        homePhone = this.homePhone;
        status = this.status;
        canPost = this.canPost != 0x00;
        canSeeAllPosts = this.canSeeAllPosts!= 0x00;
  	}

  	public static ParseUser FromVkToParse(VkPerson p)
  	{
  		ParseUser u = new ParseUser();
  		u.setUsername(p.username);

  		u.put("lastName", p.lastName);
  		u.put("firstName", p.firstName);
  		u.put("sex", p.sex);
  		u.put("birthday", p.birthday);
  		u.put("city", p.city);
  		u.put("country", p.country);
  		u.put("photoMaxOrig", p.photoMaxOrig);
        u.put("online", online);

        u.put("username" = p.username);
        u.put("hasMobile", p.hasMobile);
        u.put("mobilePhone", p.mobilePhone);
        u.put("homePhone", p.homePhone);
        u.put("status", p.status;
        u.put("canPost", p.canPost);
        u.put("canSeeAllPosts", p.canSeeAllPosts);

        return u;
  	}
}