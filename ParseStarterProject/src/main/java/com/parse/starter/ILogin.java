package pakuteam.bedlam_experiment_0_1;
public interface ILogin
{
  void attemptLogin(String email, String password);
  void attemptRegistration(String email, String pasword)
  static boolean isLoginInformationCorrect(String email, String password);
}
