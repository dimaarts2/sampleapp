package dimaarts.ru.sampleapplication.ui.list;

public enum FragmentType {
    Cats(0),
    Dogs(1);

    FragmentType (int type)
    {
        this.type = type;
    }

    private int type;

    public int getFragmentType()
    {
        return type;
    }
}
