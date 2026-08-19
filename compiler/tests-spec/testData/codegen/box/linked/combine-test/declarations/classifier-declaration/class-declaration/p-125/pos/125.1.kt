// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 125 -> sentence 125
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 125 -> sentence 125
 *                declarations, property-declaration, late-initialized-properties -> paragraph 125 -> sentence 125
 * NUMBER: 1
 * DESCRIPTION: lateinit var property throws UninitializedPropertyAccessException when read before assignment in class declaration
 */

// TESTCASE NUMBER: 1
class BareHost {
    lateinit var name: String

    fun get(): String = name

    fun isNameInitialized(): Boolean = ::name.isInitialized
}

class LazyHost {
    lateinit var token: String

    fun read(): String = token

    fun isTokenInitialized(): Boolean = ::token.isInitialized
}

fun throwsOnGet(): Boolean {
    return try {
        BareHost().get()
        false
    } catch (e: kotlin.UninitializedPropertyAccessException) {
        true
    }
}

fun throwsOnRead(): Boolean {
    return try {
        LazyHost().read()
        false
    } catch (e: kotlin.UninitializedPropertyAccessException) {
        true
    }
}

fun notInitializedBeforeUse(): String {
    val host = BareHost()
    return if (host.isNameInitialized()) "NOK: initialized early" else "OK"
}

fun tokenNotInitializedBeforeUse(): String {
    val host = LazyHost()
    return if (host.isTokenInitialized()) "NOK: token initialized early" else "OK"
}

fun box(): String {
    if (!throwsOnGet()) return "NOK: get"
    if (!throwsOnRead()) return "NOK: read"
    if (notInitializedBeforeUse() != "OK") return "NOK: not init"
    if (tokenNotInitializedBeforeUse() != "OK") return "NOK: token not init"
    return "OK"
}
