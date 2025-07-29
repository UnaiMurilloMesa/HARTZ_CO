import React, { useState } from 'react';
import { Button, ButtonProps } from '@rneui/themed';

export type HartzButtonProps = {
  backgroundColor?: string;
  activeBackgroundColor?: string;
  textColor?: string;
  title: string;
} & Partial<Omit<ButtonProps, 'title'>>;

const HartzButton: React.FC<HartzButtonProps> = ({
  backgroundColor,
  activeBackgroundColor,
  textColor,
  title,
  ...props
}) => {
  const [isPressed, setIsPressed] = useState(false);
  return (
    <Button
      title={title}
      buttonStyle={{
        backgroundColor: isPressed ? activeBackgroundColor : backgroundColor,
        borderRadius: 8,
        paddingVertical: 12,
        paddingHorizontal: 20,
      }}
      titleStyle={{
        color: textColor,
        fontSize: 16,
        fontWeight: '600',
      }}
      onPressIn={() => setIsPressed(true)}
      onPressOut={() => setIsPressed(false)}
      {...props}
    />
  );
};

export default HartzButton;
