// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 257 -> sentence 257
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 257 -> sentence 257
 *                declarations, function-declaration -> paragraph 257 -> sentence 257
 *                inheritance, inheriting -> paragraph 257 -> sentence 257
 * NUMBER: 1
 * DESCRIPTION: an interface may use reified type parameters on private inline members (exposed via non-inline wrappers); contrasts with function-declaration p-21 top-level reified and with p-25 abstract interface inline prohibition
 */

// TESTCASE NUMBER: 1
interface IdHost {
    private inline fun <reified T> id(x: T): T = x
    fun passInt(x: Int): Int = id(x)
    fun passString(x: String): String = id(x)
}

// TESTCASE NUMBER: 2
interface TypeChecker {
    private inline fun <reified T> matches(x: Any): Boolean = x is T
    fun isInt(x: Any): Boolean = matches<Int>(x)
    fun isString(x: Any): Boolean = matches<String>(x)
}

// TESTCASE NUMBER: 3
interface TypeNamer {
    private inline fun <reified T> simpleName(): String = T::class.simpleName ?: ""
    fun intName(): String = simpleName<Int>()
    fun stringName(): String = simpleName<String>()
}

class IdHostImpl : IdHost
class TypeCheckerImpl : TypeChecker
class TypeNamerImpl : TypeNamer

fun box(): String {
    val idHost: IdHost = IdHostImpl()
    if (idHost.passInt(1) != 1) return "NOK: pass-int"
    if (idHost.passString("a") != "a") return "NOK: pass-string"

    val checker: TypeChecker = TypeCheckerImpl()
    if (!checker.isInt(1)) return "NOK: is-int"
    if (checker.isInt("x")) return "NOK: not-int"
    if (!checker.isString("s")) return "NOK: is-string"

    val namer: TypeNamer = TypeNamerImpl()
    if (namer.intName() != "Int") return "NOK: int-name"
    if (namer.stringName() != "String") return "NOK: string-name"
    return "OK"
}
