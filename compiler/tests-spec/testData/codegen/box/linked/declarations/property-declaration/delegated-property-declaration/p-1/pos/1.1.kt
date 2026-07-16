// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, delegated-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: delegated read-only property getValue is invoked at runtime
 */

// TESTCASE NUMBER: 1
import kotlin.reflect.KProperty

class ReadDelegate(private val value: Int) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = value
}

val delegated: Int by ReadDelegate(42)

fun box(): String {
    return if (delegated == 42) "OK" else "NOK value=$delegated"
}
