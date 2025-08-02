import { StyleSheet } from 'react-native';
import { Image } from '@rneui/themed';
import { Button, Text, View } from '@/components/utils/Themed';
import { useRouter } from 'expo-router';

export default function InitScreen() {

  const router = useRouter();

  return (
    <View style={styles.container}>
      <View style={styles.texts}>
        <Text type='title'>Hey there!!</Text>
        <Text type='title'>Welcome to Hartz</Text>
      </View>

      <Image
        source={require('../assets/images/bears/hartz.png')}
        style={styles.image}
      />

      <View style={styles.buttons}>
        <Button
          title="Get Started"
          variant='primary'
          onPress={() => router.replace('/register')}
        />
        <Button
          title="I already have an account"
          variant='secondary'
          onPress={() => router.replace('/login')}
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
