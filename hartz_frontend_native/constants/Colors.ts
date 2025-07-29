const tone1 = '#27374D';
const tone2 = '#526D82';
const tone3 = '#9DB2BF';
const tone4 = '#DDE6ED';

export default {
  light: {
    primary: tone1,
    primaryLight: "#6A8CA1", // (Para hovers sobre primary) Para cumplir con estandar de accesibilidad WCAG AA
    secondary: tone1,
    text: tone1,
    negativeText: tone4,
    background: tone4
  },
  dark: {
    primary: tone3,
    primaryLight: "#89A6BA", // (Para hovers sobre primary) Para cumplir con estandar de accesibilidad WCAG AA
    secondary: tone4,
    text: tone4,
    negativeText: tone1,
    background: tone1
  }
};
