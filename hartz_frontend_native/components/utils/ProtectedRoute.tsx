import React, { useEffect, useState } from 'react';
import { ActivityIndicator } from 'react-native';
import { useRootNavigationState, useRouter } from 'expo-router';
import { isTokenValid } from '@/utils/tokenManager';
import { View } from './Themed';

export default function ProtectedRoute({ children }: { children: React.ReactNode }) {
  const [loading, setLoading] = useState(true);
  const router = useRouter();
  const navigationState = useRootNavigationState();

  useEffect(() => {
    if (!navigationState?.key) return;

    if (!isTokenValid()) {
      router.replace('/init');
      return;
    }
    setLoading(false);
  }, [router]);

  if (loading) {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <ActivityIndicator size="large" />
      </View>
    );
  }

  return <>{children}</>;
}
