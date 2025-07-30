// TODO: Crear nuestro input, esto es solo un placeholder para empezar a utilizarlo desde fuera
import React from 'react';
import { Input as RNEInput, InputProps } from '@rneui/themed';
import { TextStyle, ViewStyle } from 'react-native';
import { useThemeColor } from './utils/Themed';
import Colors from '@/constants/Colors';

type ThemeProps  = {
  lightColor?: string;
  darkColor?: string;
}

export type HartzInputProps = ThemeProps & Omit<InputProps, 'placeholderTextColor'> & {
  placeholderTextColor?: string;
};

const DEFAULT_INPUT_COLORS = {
  background: '#FFF',                     // color de fondo en modo claro
  border: '#6A7C8FFF',                  // borde neutro
  borderFocused: Colors.dark.secondary,     // borde cuando está enfocado
  text: '#000',                           // texto oscuro
  placeholderOpacity: '80',               // sufijo hex para 50% opacity
};

const getInputStyles = ({
  backgroundColor,
  isFocused,
}: {
  backgroundColor: string;
  isFocused: boolean;
}): { inputContainerStyle: ViewStyle; inputStyle: TextStyle } => {
  return {
    inputContainerStyle: {
      backgroundColor,
      borderWidth: 1,
      borderColor: isFocused ? DEFAULT_INPUT_COLORS.borderFocused : DEFAULT_INPUT_COLORS.border,
      borderRadius: 8,
      paddingHorizontal: 10,
    },
    inputStyle: {
      fontSize: 16,
    }
  };
};

const resolveInputColors = ({
  lightColor,
  darkColor,
  placeholderTextColor,
}: Pick<HartzInputProps, 'lightColor' | 'darkColor' | 'placeholderTextColor'>) => {
  const backgroundColor = useThemeColor({ light: lightColor, dark: darkColor }, 'background');
  const textColor = useThemeColor({ light: lightColor, dark: darkColor }, 'text');
  const placeholderColor =
    placeholderTextColor ?? textColor + DEFAULT_INPUT_COLORS.placeholderOpacity;

  return { backgroundColor, textColor, placeholderColor };
};


const HartzInput: React.FC<HartzInputProps> = ({
  lightColor,
  darkColor,
  style,
  inputContainerStyle,
  placeholderTextColor,
  ...otherProps
}) => {
  
  const [isFocused, setIsFocused] = React.useState(false);

  // 1. Resolver colores
  const { backgroundColor, textColor, placeholderColor } = resolveInputColors({
    lightColor,
    darkColor,
    placeholderTextColor,
  });

  // 2. Obtener estilos finales
  const { inputContainerStyle: baseContainer, inputStyle: baseInput } = getInputStyles({
    backgroundColor,
    isFocused,
  });

  return (
    <RNEInput
      placeholderTextColor={placeholderColor}
      inputStyle={[{ color: textColor }, baseInput, style]}
      inputContainerStyle={[baseContainer, inputContainerStyle]}
      onFocus={() => setIsFocused(true)}
      onBlur={() => setIsFocused(false)}
      {...otherProps}
    />
  );
};

export default HartzInput;