import React, { useState } from 'react';
import { Button, ButtonProps } from '@rneui/themed';
import { TextStyle } from 'react-native';

export type HartzButtonProps = {
  variant?: 'primary' | 'secondary';
  backgroundColor?: string;
  activeBackgroundColor?: string;
  textColor?: string;
  title: string;
} & Partial<Omit<ButtonProps, 'title'>>;

const DEFAULT_COLORS = {
  background: '#007BFF',
  activeBackground: '#0056b3',
  text: '#ffffff',
};

const getButtonStyles = ({
  variant,
  backgroundColor,
  activeBackgroundColor,
  isPressed,
}: {
  variant: 'primary' | 'secondary';
  backgroundColor: string;
  activeBackgroundColor: string;
  isPressed: boolean;
}) => {
  const baseStyle = {
    borderRadius: 8,
    paddingVertical: 12,
    paddingHorizontal: 20,
  };

  if (variant === 'primary') {
    return {
      type: 'solid' as const,
      buttonStyle: {
        backgroundColor: isPressed ? activeBackgroundColor : backgroundColor,
        ...baseStyle,
      },
    };
  }

  return {
    type: 'outline' as const,
    buttonStyle: {
      borderColor: isPressed ? activeBackgroundColor : backgroundColor,
      borderWidth: 2,
      backgroundColor: 'transparent',
      ...baseStyle,
    },
  };
};

const getTitleStyle = ({
  variant,
  textColor,
  backgroundColor,
  activeBackgroundColor,
  isPressed,
}: {
  variant: 'primary' | 'secondary';
  textColor: string;
  backgroundColor: string;
  activeBackgroundColor: string;
  isPressed: boolean;
}): TextStyle => {
  const color =
    variant === 'primary'
      ? textColor
      : isPressed
        ? activeBackgroundColor
        : backgroundColor;

  return {
    color,
    fontSize: 16,
    fontWeight: '600',
  };
};

const HartzButton: React.FC<HartzButtonProps> = ({
  variant = 'primary',
  backgroundColor = DEFAULT_COLORS.background,
  activeBackgroundColor = DEFAULT_COLORS.activeBackground,
  textColor = DEFAULT_COLORS.text,
  title,
  ...props
}) => {
  const [isPressed, setIsPressed] = useState(false);

  const { type, buttonStyle } = getButtonStyles({
    variant,
    backgroundColor,
    activeBackgroundColor,
    isPressed,
  });

  const titleStyle = getTitleStyle({
    variant,
    textColor,
    backgroundColor,
    activeBackgroundColor,
    isPressed,
  });

  return (
    <Button
      title={title}
      type={type}
      buttonStyle={buttonStyle}
      titleStyle={titleStyle}
      onPressIn={() => setIsPressed(true)}
      onPressOut={() => setIsPressed(false)}
      {...props}
    />
  );
};

export default HartzButton;
