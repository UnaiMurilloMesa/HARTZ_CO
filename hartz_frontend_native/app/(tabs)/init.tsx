import { StyleSheet } from 'react-native';
import { Image } from '@rneui/themed';
import { Button, Text, View } from '@/components/utils/Themed';

export default function InitScreen() {
  return (
    <View style={styles.container}>
      <View style={styles.texts}>
        <Text style={styles.title}>Hey there!!</Text>
        <Text style={styles.title}>Welcome to Hartz</Text>
      </View>

      <Image
        source={require('../../assets/images/bears/hartz.png')}
        style={styles.image}
      />

      <View style={styles.buttons}>
        <Button
          title="Get Started"
          variant='primary'
          onPress={() => console.log('Get Started Pressed')}
        />
        <Button
          title="I already have an account"
          variant='secondary'
          onPress={() => console.log('I already have an account Pressed')}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'space-evenly',
  },
  texts: {
    alignItems: 'center',
    marginBottom: 20,
  },
  title: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  buttons: {
    width: '80%',
    paddingHorizontal: 20,
    marginTop: 20,
    gap: 10,
  },
  image: {
    width: 200,
    height: 200,
    resizeMode: 'contain',
  },
});
