import React from 'react';
import { Text as RNEUIText, TextProps as RNEUITextProps } from '@rneui/themed';
import { TextStyle } from 'react-native';
export type TextType = 'title' | 'subtitle' | 'data' | 'link' | 'large' | 'small';
export interface HartzTextProps extends RNEUITextProps {
  type: TextType;
}

interface AppTextProps extends RNEUITextProps {
  type?: TextType;
}

const typeStyles: Record<TextType, TextStyle> = {
  title: {
    fontSize: 28,
    fontWeight: 'bold',
  },
  subtitle: {
    fontSize: 25,
    fontWeight: 'bold',
  },
  data: {
    fontSize: 16,
    fontWeight: 'bold',
  },
  link: {
    fontSize: 16,
    fontWeight: '500',
    textDecorationLine: 'underline',
    color: '#1e90ff',
  },
  large: {
    fontSize: 25
  },
  small: {
    fontSize: 16
  }
};

export default function HartzText({
  children,
  type = 'data',
  style,
  ...rest
}: HartzTextProps) {
  return (
    <RNEUIText style={[typeStyles[type], style]} {...rest}>
      {children}
    </RNEUIText>
  );
}
