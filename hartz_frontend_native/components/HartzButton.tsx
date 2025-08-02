import React, { useState } from 'react';
import { Button, ButtonProps } from '@rneui/themed';
import { TextStyle } from 'react-native';

export type HartzButtonProps = {
  variant: ButtonVariant;
  backgroundColor: string;
  activeBackgroundColor: string;
  textColor: string;
  title: string;
} & Partial<Omit<ButtonProps, 'title'>>;

export type ButtonVariant = 'primary' | 'secondary' | 'transparent';

const getButtonStyles = ({
  variant,
  backgroundColor,
  activeBackgroundColor,
  isPressed,
}: {
  variant: ButtonVariant;
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
  if (variant === 'secondary') {
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
  }
  if (variant === 'transparent') {
    return {
      type: 'outline' as const,
      buttonStyle: {
        borderWidth: 0,
        ...baseStyle
      }
    }
  }
  else throw new Error("Variant not recognized");
  
};

const getTitleStyle = ({
  variant,
  textColor,
  backgroundColor,
  activeBackgroundColor,
  isPressed,
}: {
  variant: ButtonVariant;
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
  variant,
  backgroundColor,
  activeBackgroundColor,
  textColor,
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
