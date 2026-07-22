// WITH_STDLIB
// WITH_REFLECT

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, overview -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: RUNTIME-retained annotation on function is readable via Kotlin reflection; BINARY-retained annotation is not visible at runtime
 */

// TESTCASE NUMBER: 1

import kotlin.reflect.jvm.javaMethod

@Retention(AnnotationRetention.RUNTIME)
annotation class RuntimeMarker17002(val value: Int, val label: String)

@Retention(AnnotationRetention.BINARY)
annotation class BinaryMarker17002(val value: Int)

@RuntimeMarker17002(42, "fn")
@BinaryMarker17002(99)
fun annotatedFunction17002(): String = "body"

fun box(): String {
    val kFunction = ::annotatedFunction17002
    val runtimeAnn = kFunction.annotations.filterIsInstance<RuntimeMarker17002>().singleOrNull()
        ?: return "NOK: RUNTIME annotation missing from Kotlin reflection"
    if (runtimeAnn.value != 42) return "NOK: unexpected RUNTIME value ${runtimeAnn.value}"
    if (runtimeAnn.label != "fn") return "NOK: unexpected RUNTIME label ${runtimeAnn.label}"
    if (kFunction.annotations.any { it is BinaryMarker17002 }) {
        return "NOK: BINARY annotation should not be visible via Kotlin reflection"
    }
    val javaMethod = kFunction.javaMethod ?: return "NOK: javaMethod missing"
    if (javaMethod.getAnnotation(BinaryMarker17002::class.java) != null) {
        return "NOK: BINARY annotation should not be visible via Java reflection"
    }
    val javaRuntime = javaMethod.getAnnotation(RuntimeMarker17002::class.java)
        ?: return "NOK: RUNTIME annotation missing from Java reflection"
    if (javaRuntime.value != 42 || javaRuntime.label != "fn") return "NOK: unexpected Java reflection payload"
    return "OK"
}
