// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 85 -> sentence 85
 * PRIMARY LINKS: declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 85 -> sentence 85
 * NUMBER: 1
 * DESCRIPTION: cached companion factory returns Singleton with Int id
 * HELPERS: checkType
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

fun case1() {
    checkSubtype<Singleton>(Singleton.get())
    checkSubtype<Int>(Singleton.get().id)
}
