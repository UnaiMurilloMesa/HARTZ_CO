import React from 'react';
import { View, StyleSheet, StyleProp, ViewStyle } from 'react-native';

type HeatMapProps = {
  data: number[];
  columns: number;
  color: string;
  cellSize?: number;
  style?: StyleProp<ViewStyle>;
};

const HeatMap: React.FC<HeatMapProps> = ({
  data,
  columns,
  color,
  cellSize = 20,
  style,
}) => {
  const hexToRgb = (hex: string) => {
    let c = hex.replace('#', '');
    if (c.length === 3) {
      c = c.split('').map(ch => ch + ch).join('');
    }
    const bigint = parseInt(c, 16);
    const r = (bigint >> 16) & 255;
    const g = (bigint >> 8) & 255;
    const b = bigint & 255;
    return `${r}, ${g}, ${b}`;
  };

  const baseColor = color.startsWith('#') ? hexToRgb(color) : color;

  return (
    <View
      style={[
        styles.grid,
        { width: columns * (cellSize + 2) },
        style,
      ]}
    >
      {data.map((value, index) => {
        const backgroundColor = `rgba(${baseColor}, ${value})`;

        return (
          <View
            key={index}
            style={[
              styles.cell,
              {
                width: cellSize,
                height: cellSize,
                backgroundColor,
                margin: 1,
              },
            ]}
          />
        );
      })}
    </View>
  );
};

const styles = StyleSheet.create({
  grid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
  },
  cell: {
    borderRadius: 3,
  },
});

export default HeatMap;
