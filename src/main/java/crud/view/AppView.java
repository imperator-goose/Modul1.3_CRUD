package crud.view;



public class AppView {
    SkillView skillView = new SkillView();
    SpecialityView specialtyView = new SpecialityView();
    DeveloperView developerView = new DeveloperView();
    public void mainWorkProgram(){
        developerView.workProgram();
        skillView.workProgram();
        specialtyView.workProgram();
    }

    public static AppView appView;

    private AppView(){}

    public static AppView getAppView(){
        if (appView == null){
            appView = new AppView();
        }
        return appView;
    }
}