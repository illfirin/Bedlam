package pakuteam.bedlam_experiment_0_1;
//add butterknife, sentry, java file dependencies
interface IFileManager
{
  static void saveFile(File f);
  static File loadFile();
}

public class FileManagerHelper implements IFileManager
{

    @Bind(R.id.fileList)
    protected ListView mFileListView;
    @BInd(R.id.FileName)
    protected EditText file_name;
    public static void saveFile(File file, Context cntx)
    {
        mDialog = new Dialog(cntx);
        mDialog.setContentView(R.layout.dialog);
        mDialog.setTitle(mCurrentLocation.getAbsoletePath());
    }

    public static File loadFile()
    {

    }

    private void prepareFilesList()
    {
        mFileListView.setOnClickListener( new OnItemClickListener()
        {
            @Override
            {
              public void OnItemClick(final AdapterView parent, final View view, int position, final long id)
              {
                  file_name.setText("");
                  if(id == 0)
                  {
                      final String parentLocation = mCurrentLocation.getParent();
                      if(parentLocation != null)
                      {
                        String fileFilter = ((TextView)FilterSpinner.getSelectedView()).getText().toString();
                        mCurrentLocation = new File(parentLocation);
                        makeList(mCurrentLocation, fileFilter);
                      }
                      else
                      {
                        onItemSelect(parent, position);
                      }
                  }
                  else
                  {
                    onItemSelect(parent, position);
                  }
              }
            }
        });
        String filtr = mFilterSpinner.getSelectedItem().toString();
        makeList(mCurrentLocation, filtr);
    }

    private void makeList(final File location, final String filter)
    {
      final ArrayList<FileData> fileList new ArrayList<FileData>
    }
}
