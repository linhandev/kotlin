// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 85 -> sentence 85
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 85 -> sentence 85
 * NUMBER: 1
 * DESCRIPTION: private primary constructor with cached companion singleton identity
 */

// TESTCASE NUMBER: 1
class Singleton private constructor(val id: Int) {
    companion object {
        private var cached: Singleton? = null
        fun get(): Singleton {
            val existing = cached
            if (existing != null) return existing
            val created = Singleton(1)
            cached = created
            return created
        }
    }
}

fun test(): Boolean = Singleton.get() === Singleton.get() && Singleton.get().id == 1

fun box(): String {
    if (!test()) return "NOK"
    return "OK"
}
