#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package  ${package}.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

public class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {
  public StringToEnumConverterFactory() {
  }

  public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
    return new StringToEnumConverterFactory.StringToEnum(getEnumType(targetType));
  }

  private static Class<?> getEnumType(Class<?> targetType) {
    Class enumType;
    for(enumType = targetType; enumType != null && !enumType.isEnum(); enumType = enumType.getSuperclass()) {
    }

    if (enumType == null) {
      throw new IllegalArgumentException("The target type " + targetType.getName() + " does not refer to an enum");
    } else {
      return enumType;
    }
  }

  private class StringToEnum<T extends Enum> implements Converter<String, T> {
    private final Class<T> enumType;

    public StringToEnum(Class<T> enumType) {
      this.enumType = enumType;
    }

    public T convert(String source) {
      if (source != null && source.length() != 0) {
        try {
          return Enum.valueOf(this.enumType, source.trim());
        } catch (Exception var3) {
          return null;
        }
      } else {
        return null;
      }
    }
  }
}