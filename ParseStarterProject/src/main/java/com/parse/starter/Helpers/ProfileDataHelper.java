package pakuteam.bedlam_experiment_0_1;
//TODO: create interface for this file
/**
 * ProfileDataHelper
 */
public class ProfileDataHelper
{
    public static void changeProfileName()
    {

    }

    public static void changeProfilePassword()
    {

    }

    public static void changeProfileImage()
    {
      
    }

    public static Boolean isEmailValid(String email)
    {
        //TODO: Check Email with regular expressions
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password)
    {
        List<Character> pass = password.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
        boolean HasUpper = pass.stream().HasAny(e -> Character.isUpperCase(e) );
        //Check for length and containing of UpperCase characrets
        return password.length() > 6 && HasUpper ?? true: false;
    }
}
