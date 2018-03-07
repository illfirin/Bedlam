package pakuteam.bedlam_experiment_0_1;
public interface ILogin
{
  void attemptLogin(String email, String password);
  static boolean isLoginInformationCorrect(String email, String password);
}
