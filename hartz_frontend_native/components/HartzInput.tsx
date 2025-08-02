// TODO: Crear nuestro input, esto es solo un placeholder para empezar a utilizarlo desde fuera
import React from 'react';
import { Input as RNEInput, InputProps } from '@rneui/themed';
import { TextStyle, ViewStyle } from 'react-native';
import { color } from '@rneui/base';

export type HartzInputProps = Omit<InputProps, 'placeholderTextColor'> 
& {
  placeholderColor?: string;
  placeholderBorderColor?: string;
  placeholderBorderActiveColor?: string;
  textColor?: string;
};

const DEFAULT_INPUT_COLORS = {
  backgroundColor: '#36802dff',                    // color de fondo en modo claro
  borderNormal: '#9017b4ff',                        // borde neutro
  borderFocused: '#04ffffff',                 // borde cuando está enfocado
  textColor: '#ecff3dff',                          // texto oscuro
};


const getColors = (
  {
  placeholderColor,
  placeholderBorderColor,
  placeholderBorderActiveColor,
  textColor,
  isFocused
}:{
  placeholderColor?: string;
  placeholderBorderColor?: string;
  placeholderBorderActiveColor?: string;
  textColor?: string;
  isFocused?: boolean;
}) => {
  return {
    placeHolderStyle: {
      backgroundColor: placeholderColor,
      borderColor: isFocused ? placeholderBorderActiveColor : placeholderBorderColor,
      borderWidth: 1,
      borderRadius: 8,
      paddingHorizontal: 10,
    },
    inputTextStyle: {
      color: textColor,
      fontSize: 16,
    }
  };
}


const HartzInput: React.FC<HartzInputProps> = ({
  placeholderColor,
  placeholderBorderColor,
  placeholderBorderActiveColor,
  textColor,
  ...otherProps
}) => {
  
  const [isFocused, setIsFocused] = React.useState(false);

  const { placeHolderStyle, inputTextStyle } = getColors(
    {
      placeholderColor,
      placeholderBorderColor,
      placeholderBorderActiveColor,
      textColor,
      isFocused
    }
  ) as {
    placeHolderStyle: ViewStyle;
    inputTextStyle: TextStyle;
  };

  return (
    <RNEInput
      inputStyle= {inputTextStyle}
      inputContainerStyle={placeHolderStyle}
      onFocus={() => setIsFocused(true)}
      onBlur={() => setIsFocused(false)}
      {...otherProps}
    />
  );
};

export default HartzInput;