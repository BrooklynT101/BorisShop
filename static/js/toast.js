// toast.js
export const Toast = {
    template: `
        <div v-if="visible" class="toast" :class="{ 'show': visible }" @transitionend="handleTransitionEnd">
            {{ message }}
        </div>
    `,
    props: {
        message: {
            type: String,
            default: ''
        },
        visible: {
            type: Boolean,
            default: false
        }
    },
    methods: {
        handleTransitionEnd() {
            if (!this.visible) {
                this.$emit('close');
            }
        }
    },
    watch: {
        visible(newVal) {
            if (newVal) {
                setTimeout(() => {
                    this.$emit('close');
                }, 3000); // Hide after 3 seconds
            }
        }
    }
};
