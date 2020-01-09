package com.thinging.project.persistance;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HstoreUserType implements UserType {

    /**
     * PostgreSQL {@code hstore} field separator token.
     */
    private static final String HSTORE_SEPARATOR_TOKEN = "=>";

    /**
     * {@link Pattern} used to find and split {@code hstore} entries.
     */
    private static final Pattern HSTORE_ENTRY_PATTERN = Pattern.compile(String.format("\"(.*)\"%s\"(.*)\"", HSTORE_SEPARATOR_TOKEN));

    /**
     * The PostgreSQL value for the {@code hstore} data type.
     */
    public static final int HSTORE_TYPE = 1111;

    @Override
    public int[] sqlTypes() {
        return new int[]{HSTORE_TYPE};
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class returnedClass() {
        return Map.class;
    }

    @Override
    public boolean equals(final Object x, final Object y) throws HibernateException {
        return x.equals(y);
    }

    @Override
    public int hashCode(final Object x) throws HibernateException {
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        return convertToEntityAttribute(resultSet.getString(strings[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        preparedStatement.setObject(i, convertToDatabaseColumn((Map<String, Object>) o), HSTORE_TYPE);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object deepCopy(final Object value) throws HibernateException {
        return new HashMap<String, Object>(((Map<String, Object>) value));
    }

    @Override
    public boolean isMutable() {
        return true;
    }

    @Override
    public Serializable disassemble(final Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(final Serializable cached, final Object owner)
            throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(final Object original, final Object target, final Object owner)
            throws HibernateException {
        return original;
    }


    private String convertToDatabaseColumn(final Map<String, Object> attribute) {
        final StringBuilder builder = new StringBuilder();
        for (final Map.Entry<String, Object> entry : attribute.entrySet()) {
            if (builder.length() > 1) {
                builder.append(", ");
            }
            builder.append("\"");
            builder.append(entry.getKey());
            builder.append("\"");
            builder.append(HSTORE_SEPARATOR_TOKEN);
            builder.append("\"");
            builder.append(entry.getValue().toString());
            builder.append("\"");
        }
        return builder.toString();
    }

    private Map<String, Object> convertToEntityAttribute(final String dbData) {
        final Map<String, Object> data = new HashMap<>();
        if (dbData != null) {
            final StringTokenizer tokenizer = new StringTokenizer(dbData, ",");
            while (tokenizer.hasMoreTokens()) {
                final Matcher matcher = HSTORE_ENTRY_PATTERN.matcher(tokenizer.nextToken().trim());
                if (matcher.find()) {
                    data.put(matcher.group(1), matcher.group(2));
                }
            }
        }
        return data;
    }
}
