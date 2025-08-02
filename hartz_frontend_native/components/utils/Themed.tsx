/**
 * Learn more about Light and Dark modes:
 * https://docs.expo.io/guides/color-schemes/
 */

import { Divider as DefaultDivider, DividerProps } from '@rneui/themed';
import { View as DefaultView, ScrollView as DefaultScrollView } from 'react-native';
import HartzText, { HartzTextProps } from '@/components/HartzText'
import HartzButton, { ButtonVariant, HartzButtonProps } from '@/components/HartzButton';
import Colors from '@/constants/Colors';
import { useColorScheme } from './useColorScheme';
import HartzInput, { HartzInputProps } from '../HartzInput';
import type { ButtonProps as RNEUIButtonProps } from '@rneui/base/dist/Button/Button';

type ThemeProps = {
  lightColor?: string;
  darkColor?: string;
};

type ButtonProps = ThemeProps & {
  title: string,
  variant: ButtonVariant,
  backgroundColor?: string,
  activeBackgroundColor?: string,
  textColor?: string,
} & Partial<Omit<RNEUIButtonProps, 'title'>>;;
type ViewProps = ThemeProps & DefaultView['props'];
type ScrollViewProps = ThemeProps & DefaultScrollView['props']

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

export function Text(props: HartzTextProps & ThemeProps) {
  const { style, lightColor, darkColor, type = 'data', ...otherProps } = props;
  const color = useThemeColor({ light: lightColor, dark: darkColor }, 'text');

  return (
    <HartzText type={type} style={[{ color }, style]} {...otherProps} />
  );
}

export function View(props: ViewProps) {
  const { style, lightColor, darkColor, ...otherProps } = props;
  const backgroundColor = useThemeColor({ light: lightColor, dark: darkColor }, 'background');

  return <DefaultView style={[{ backgroundColor }, style]} {...otherProps} />;
}

export function Input(props: HartzInputProps) {
  const {
    placeholderColor,
    placeholderBorderColor,
    placeholderBorderActiveColor,
    textColor,
    ...otherProps
  } = props;

  const resolvedPlaceholderColor =
    placeholderColor ?? useThemeColor({}, 'background');
  const resolvedPlaceholderBorderColor =
    placeholderBorderColor ?? useThemeColor({}, 'primary');
  const resolvedPlaceholderBorderActiveColor =
    placeholderBorderActiveColor ?? useThemeColor({}, 'secondary');
  const resolvedTextColor =
    textColor ?? useThemeColor({}, 'text');

  return (<HartzInput
    placeholderColor={resolvedPlaceholderColor}
    placeholderBorderColor={resolvedPlaceholderBorderColor}
    placeholderBorderActiveColor={resolvedPlaceholderBorderActiveColor}
    textColor={resolvedTextColor}
    {...otherProps} />
  );
}

export function ScrollView(props: ScrollViewProps) {
  const { style, lightColor, darkColor, ...otherProps } = props;
  const backgroundColor = useThemeColor({ light: lightColor, dark: darkColor }, 'background');

  return <DefaultScrollView style={[{ backgroundColor }, style]} {...otherProps} />;
}

export function Button(props: ButtonProps) {
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

export function Divider(props: DividerProps) {
  return (
    <DefaultDivider
      color={useThemeColor({}, 'primary')}
      width={1}
      style={{ width: '90%' }}
      {...props}>
    </DefaultDivider>
  );
}
