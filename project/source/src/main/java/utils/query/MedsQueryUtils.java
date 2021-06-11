package utils.query;

public class MedsQueryUtils {
    public static final String  MED_SELECT_QUERY = "SELECT n FROM Drug n";
    public static final String  DELETE_NUME_QUERY ="Delete from Drug m where m.name=:name";
    public static final String  UPDATE_PRICE_QUERY ="Update Drug s SET s.price=:price where s.name=:name";
    public static final String  UPDATE_CANT_QUERY ="Update Drug s SET s.stock=:cant where s.name=:name";
    public static final String  NAME_QUERY = "Select m From Drug m where m.name=:name";
    public static final String  UPDATE_RATING_DISCOUNT_QUERY = "Update Drug s SET s.discount=:discount, s.rating=:rating, s.quantityDiscounted=:quantityDiscounted where s.name=:name";
    public static final String  UPDATE_RATING_QUERY = "Update Drug s SET s.rating=:rating, s.nrReviews=:nrReviews where s.name=:name";
}

