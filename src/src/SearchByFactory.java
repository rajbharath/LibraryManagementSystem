package src;

/**
 * Created by ganeswari on 10/12/14.
 */
public class SearchByFactory {

    public static SearchBy create(SearchByType searchByType) {
        switch (searchByType)
        {
            case AUTHOR:
                return new SearchByAuthor();

            case TITLE:
                break;
        }
        return null;
    }
}
