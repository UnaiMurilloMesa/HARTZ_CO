import React from 'react';
import { Input as RNEInput, InputProps } from '@rneui/themed';
import { TextStyle, ViewStyle } from 'react-native';

export type HartzInputProps = Omit<InputProps, 'placeholderTextColor'> 
& {
  placeholderColor?: string;
  placeholderBorderColor?: string;
  placeholderBorderActiveColor?: string;
  textColor?: string;
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