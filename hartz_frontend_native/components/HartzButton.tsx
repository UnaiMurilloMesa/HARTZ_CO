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
      containerStyle: {
        borderRadius: 8,
        boxShadow: '0px 2px 5px rgba(0, 8, 46, 0.4)'
      },
    };
  }

  return {
    type: 'outline' as const,
    buttonStyle: {
      borderRadius: 8,
      borderColor: isPressed ? activeBackgroundColor : backgroundColor,
      borderWidth: 2,
      backgroundColor: 'transparent',
      ...baseStyle,
    },
    containerStyle: {}
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
  variant = 'secondary',
  backgroundColor = DEFAULT_COLORS.background,
  activeBackgroundColor = DEFAULT_COLORS.activeBackground,
  textColor = DEFAULT_COLORS.text,
  title,
  ...props
}) => {
  const [isPressed, setIsPressed] = useState(false);

  const { type, buttonStyle, containerStyle } = getButtonStyles({
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
      containerStyle={containerStyle}
      titleStyle={titleStyle}
      onPressIn={() => setIsPressed(true)}
      onPressOut={() => setIsPressed(false)}
      {...props}
    />
  );
};

export default HartzButton;
