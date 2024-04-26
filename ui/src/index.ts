import { definePlugin } from "@halo-dev/console-shared";
import { NeteaseMusicExtension } from "./editor";

export default definePlugin({
  name: "plugin-netease-music",
  components: {},
  extensionPoints: {
    "default:editor:extension:create": () => {
      return [NeteaseMusicExtension];
    },
  },
});
