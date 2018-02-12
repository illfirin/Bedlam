package pakuteam.bedlam_experiment_0_1;
import java.io.File;

public class FileUtils
{
    public static final String FILTER_ALLOW_ALL = "*.*";
    //method that checks whether the file is accepted or not
    public static boolean accept(final File file, final String filter)
    {
      if(filter.compareTo(FILTER_ALLOW_ALL) == 0)
      {
        return true;
      }
      if(file.isDirectory())
          int lastIndexOfPoint = file.getName().lastIndexOf('.');
      if(lastIndexOfPoint == -1)
      {
        return false;
      }
      String fileType = file.getName().substring(lastIndexOfPoint).toLowerCase();
      return fileType.compareTo(filter) == 0;
    }
}
