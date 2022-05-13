<template>
  <div class="row">
    <div class="auth-layout row align-content--center">
      <div class="flex xs12 pa-3 flex-center">
        <router-link class="py-5 flex-center" to="/">
          <!-- <vuestic-logo height="32" /> -->
          <div class="d-flex align--center">
            <img src="@/../public/img/landing-page/woori-ai-logo/child-boy.png" alt="logo" class="auth-logo">
            <div class="f-auth mr-2 ml-2">우리A.I</div>
            <img src="@/../public/img/landing-page/woori-ai-logo/child-girl.png" alt="logo" class="auth-logo">
          </div>
        </router-link>
      </div>

      <div class="flex xs12 pa-3">
        <div class="d-flex justify--center">
          <va-card class="auth-layout__card">
            <va-card-content>
              <va-tabs v-model="tabIndex" center>
                <template #tabs>
                  <va-tab name="signup">{{
                    $t("auth.createNewAccount")
                  }}</va-tab>
                  <va-tab name="login">{{ $t("auth.login") }}</va-tab>
                </template>
              </va-tabs>

              <va-separator />

              <div class="pa-3">
                <router-view />
              </div>
            </va-card-content>
          </va-card>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import VuesticLogo from "@/components/vuestic-logo";

export default {
  name: "AuthLayout",
  components: { VuesticLogo },
  data() {
    return {
      selectedTabIndex: 0
    };
  },
  computed: {
    tabIndex: {
      set(tabName) {
        this.$router.push({ name: tabName });
      },
      get() {
        return this.$route.name;
      }
    }
  }
};
</script>

<style lang="scss">
.auth-layout {
  min-height: 100vh;
  background-image: linear-gradient(
    to right,
    var(--va-background),
    var(--va-white)
  );

  &__card {
    width: 100%;
    max-width: 600px;
  }
}

.auth-logo {
  width: 3rem;
}

.f-auth {
  font-weight: bold;
  font-size: 2.5rem;
  color: #303030;
}

</style>
