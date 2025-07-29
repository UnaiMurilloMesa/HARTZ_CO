/**
 * Learn more about Light and Dark modes:
 * https://docs.expo.io/guides/color-schemes/
 */

import { Text as DefaultText, TextProps as RNETextProps } from '@rneui/themed';
import { View as DefaultView } from 'react-native';
import HartzButton, { HartzButtonProps } from '@/components/HartzButton';


import Colors from '@/constants/Colors';
import { useColorScheme } from './useColorScheme';

type ThemeProps = {
  lightColor?: string;
  darkColor?: string;
};

type TextProps = ThemeProps & RNETextProps;
type ViewProps = ThemeProps & DefaultView['props'];
type ButtonProps = ThemeProps & HartzButtonProps;

export function useThemeColor(
  props: { light?: string; dark?: string },
  colorName: keyof typeof Colors.light & keyof typeof Colors.dark
) {
  const theme = useColorScheme() ?? 'light';
  const colorFromProps = props[theme];

  if (colorFromProps) {
    return colorFromProps;
  } else {
    return Colors[theme][colorName];
  }
}

export function Text(props: TextProps) {
  const { style, lightColor, darkColor, ...otherProps } = props;
  const color = useThemeColor({ light: lightColor, dark: darkColor }, 'text');

  return <DefaultText style={[{ color }, style]} {...otherProps} />;
}

export function View(props: ViewProps) {
  const { style, lightColor, darkColor, ...otherProps } = props;
  const backgroundColor = useThemeColor({ light: lightColor, dark: darkColor }, 'background');

  return <DefaultView style={[{ backgroundColor }, style]} {...otherProps} />;
}

export function Button(props: HartzButtonProps) {
  const {
    backgroundColor,
    activeBackgroundColor,
    textColor,
    ...otherProps
  } = props;

  const resolvedBackgroundColor =
    backgroundColor ?? useThemeColor({}, 'primary');
  const resolvedActiveBackgroundColor =
    activeBackgroundColor ?? useThemeColor({}, 'primaryLight');
  const resolvedTextColor =
    textColor ?? useThemeColor({}, 'negativeText');

  return (
    <HartzButton
      backgroundColor={resolvedBackgroundColor}
      activeBackgroundColor={resolvedActiveBackgroundColor}
      textColor={resolvedTextColor}
      {...otherProps}
    />
  );
}
