package ca.shorturl.business;

import ca.shorturl.dao.ShortURLDAO;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
@LocalBean
public class ShortURLBusiness {

    /**
     * This is the of possibles caracteres in the URL
     * <p>
     * <p>
     * This is the range of possibilities using 62 caracteres with N caracteres in the short URL
     * 62^1 = 62
     * 62^2 = 3.844
     * 62^3 = 238.328
     * 62^4 = 14.776.336
     * 62^5 = 916.132.832
     * 62^6 = 56.800.235.584
     * .............
     */
    private static final char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();


    @Inject
    ShortURLDAO shortURLDAO;

    /**
     * Public method to create a new ShortURL
     * It call's our DAO to create a new line in our table shorturl.
     * The table in the database has a ID column that auto increment the value of the id
     *
     * @param longURL
     * @return a short url created
     */
    public String createNewShortURL(String longURL) {
        Long id = shortURLDAO.createNew(longURL);
        if (id != null) {
            String url = convertToShortURL(id.longValue());
            shortURLDAO.setShortURL(id, url);
            return url;
        }
        return null;

    }

    /**
     * Public method to reverse a SHORT URL to LONG URL
     * First, we generate a long ID using a short url, after, we go to database and get a LONGURL
     *
     * @param shortURL
     * @return a longurl
     */
    public String getLongURL(String shortURL) {
        Long id = convertToID(shortURL);
        return null != id ? shortURLDAO.getLongURL(id) : null;
    }

    /**
     * This method use the ID generate in MySQL database to create a new short url
     *
     * @param id
     * @return a new short url created
     */
    private String convertToShortURL(long id) {

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (id > 0) {
            sb.append(chars[(int) (id % 62)]);
            id = id / 62;
        }

        return sb.toString();
    }

    /**
     * @param shortURL = a SHORT URL to get the ID of that.
     *                 <p>
     *                 To get the ID we use the chars[] variable in this class.
     *                 Based in that variable, we considered that the index of started lowercase caracteres is 0,
     *                 the started index for uppercase caracteres is 26 and the started index for number is 52
     * @return the ID of the caracter
     */
    private long convertToID(String shortURL) {
        long result = 0;

        for (int i = 0; i < shortURL.length(); i++) {
            char charValue = shortURL.charAt(i);
            if ('a' <= charValue && charValue <= 'z')
                result = result * 62 + charValue - 'a';
            if ('A' <= charValue && charValue <= 'Z')
                result = result * 62 + charValue - 'A' + 26;
            if ('0' <= charValue && charValue <= '9')
                result = result * 62 + charValue - '0' + 52;
        }
        return result;
    }

}
