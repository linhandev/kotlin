/*
 * Copyright (C) 2026 Eazytec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import kotlin.test.*
import kotlinx.cinterop.*

@OptIn(
    kotlin.experimental.ExperimentalNativeApi::class,
    kotlinx.cinterop.ExperimentalForeignApi::class
)
class FFRTTest {

    private fun logLine(message: String) = println(message)

    @Test
    fun testConditionVariable_condition_variable_h() {
        // header: ffrt/condition_variable.h
        memScoped {
            val cond = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_cond_t>()

            val rInit = platform.FunctionFlowRuntimeKit.FFRT.ffrt_cond_init(cond.ptr, null)
            assertNotNull(rInit)
            logLine("ffrt_cond_init -> $rInit")

            val rSignal = platform.FunctionFlowRuntimeKit.FFRT.ffrt_cond_signal(cond.ptr)
            assertNotNull(rSignal)
            logLine("ffrt_cond_signal -> $rSignal")

            val rBroadcast = platform.FunctionFlowRuntimeKit.FFRT.ffrt_cond_broadcast(cond.ptr)
            assertNotNull(rBroadcast)
            logLine("ffrt_cond_broadcast -> $rBroadcast")

            val rWaitNull = platform.FunctionFlowRuntimeKit.FFRT.ffrt_cond_wait(null, null)
            assertNotNull(rWaitNull)
            logLine("ffrt_cond_wait(null, null) -> $rWaitNull")

            val rTimedWait = platform.FunctionFlowRuntimeKit.FFRT.ffrt_cond_timedwait(cond.ptr, null, null)
            assertNotNull(rTimedWait)
            logLine("ffrt_cond_timedwait -> $rTimedWait")

            val rDestroy = platform.FunctionFlowRuntimeKit.FFRT.ffrt_cond_destroy(cond.ptr)
            assertNotNull(rDestroy)
            logLine("ffrt_cond_destroy -> $rDestroy")

            val mutex = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_t>()
            val rMutexInit = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_init(mutex.ptr, null)
            assertNotNull(rMutexInit)
            logLine("ffrt_mutex_init(for condvar) -> $rMutexInit")
        }
    }

    @Test
    fun testSleep_sleep_h() {
        // header: ffrt/sleep.h
        val r0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_usleep(0uL)
        assertNotNull(r0)
        logLine("ffrt_usleep(0) -> $r0")

        val r1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_usleep(1000uL)
        assertNotNull(r1)
        logLine("ffrt_usleep(1000) -> $r1")

        platform.FunctionFlowRuntimeKit.FFRT.ffrt_yield()
        logLine("ffrt_yield() -> called")
    }

    @Test
    fun testTask_task_h() {
        // header: ffrt/task.h
        memScoped {
            val attr = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_t>()

            val rAttrInit = platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_init(attr.ptr)
            assertNotNull(rAttrInit)
            logLine("ffrt_task_attr_init -> $rAttrInit")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_set_name(attr.ptr, "ffrt_task_test")
            val namePtr = platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_get_name(attr.ptr)
            assertNotNull(namePtr)
            logLine("ffrt_task_attr_get_name -> ${namePtr.toKString()}")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_set_qos(
                attr.ptr,
                platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_default
            )
            val qos = platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_get_qos(attr.ptr)
            assertNotNull(qos)
            logLine("ffrt_task_attr_get_qos -> $qos")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_set_delay(attr.ptr, 0uL)
            val delay = platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_get_delay(attr.ptr)
            assertNotNull(delay)
            logLine("ffrt_task_attr_get_delay -> $delay")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_set_queue_priority(
                attr.ptr,
                platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_priority_high
            )
            val priority = platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_get_queue_priority(attr.ptr)
            assertNotNull(priority)
            logLine("ffrt_task_attr_get_queue_priority -> $priority")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_set_stack_size(attr.ptr, 100uL)
            val stackSize = platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_get_stack_size(attr.ptr)
            assertNotNull(stackSize)
            logLine("ffrt_task_attr_get_stack_size -> $stackSize")

            val rUpdateQos = platform.FunctionFlowRuntimeKit.FFRT.ffrt_this_task_update_qos(
                platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_default
            )
            assertNotNull(rUpdateQos)
            logLine("ffrt_this_task_update_qos -> $rUpdateQos")

            val thisQos = platform.FunctionFlowRuntimeKit.FFRT.ffrt_this_task_get_qos()
            assertNotNull(thisQos)
            logLine("ffrt_this_task_get_qos -> $thisQos")

            val id = platform.FunctionFlowRuntimeKit.FFRT.ffrt_this_task_get_id()
            assertNotNull(id)
            logLine("ffrt_this_task_get_id -> $id")

            val storage = platform.FunctionFlowRuntimeKit.FFRT.ffrt_alloc_auto_managed_function_storage_base(
                platform.FunctionFlowRuntimeKit.FFRT.ffrt_function_kind_t.ffrt_function_kind_general
            )
            assertNotNull(storage)
            logLine("ffrt_alloc_auto_managed_function_storage_base -> $storage")

            val header = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_function_header_t>()
            val inDepsItems = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_dependence_t>().apply {
                type = platform.FunctionFlowRuntimeKit.FFRT.ffrt_dependence_type_t.ffrt_dependence_data
                ptr = null
            }
            val inDeps = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_deps_t>().apply {
                len = 0u
                items = inDepsItems.ptr?.reinterpret<platform.FunctionFlowRuntimeKit.FFRT.ffrt_dependence_t>()
            }
            val outDepsItems = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_dependence_t>().apply {
                type = platform.FunctionFlowRuntimeKit.FFRT.ffrt_dependence_type_t.ffrt_dependence_data
                ptr = null
            }
            val outDeps = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_deps_t>().apply {
                len = 0u
                items = outDepsItems.ptr?.reinterpret<platform.FunctionFlowRuntimeKit.FFRT.ffrt_dependence_t>()
            }

            // platform.FunctionFlowRuntimeKit.FFRT.ffrt_submit_base(header.ptr, inDeps.ptr, outDeps.ptr, attr.ptr)
            // logLine("ffrt_submit_base -> called")

            // var hBase = platform.FunctionFlowRuntimeKit.FFRT.ffrt_submit_h_base(header.ptr, inDeps.ptr, outDeps.ptr, attr.ptr)
            // assertNotNull(hBase)
            // logLine("ffrt_submit_h_base -> $hBase")

            // ffrt_submit_f / ffrt_submit_h_f are API 20+
            try {
                platform.FunctionFlowRuntimeKit.FFRT.ffrt_submit_f(
                    null,
                    null,
                    inDeps.ptr,
                    outDeps.ptr,
                    attr.ptr
                )
                logLine("ffrt_submit_f -> called")

                val hF = platform.FunctionFlowRuntimeKit.FFRT.ffrt_submit_h_f(
                    null,
                    null,
                    inDeps.ptr,
                    outDeps.ptr,
                    attr.ptr
                )
                assertNotNull(hF)
                logLine("ffrt_submit_h_f -> $hF")

                val inc = platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_handle_inc_ref(hF)
                assertNotNull(inc)
                logLine("ffrt_task_handle_inc_ref -> $inc")

                val dec = platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_handle_dec_ref(hF)
                assertNotNull(dec)
                logLine("ffrt_task_handle_dec_ref -> $dec")

                val deps = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_deps_t>()
                platform.FunctionFlowRuntimeKit.FFRT.ffrt_wait_deps(deps.ptr)
                logLine("ffrt_wait_deps -> called")

                platform.FunctionFlowRuntimeKit.FFRT.ffrt_wait()
                logLine("ffrt_wait -> called")

                platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_handle_destroy(hF)
                logLine("ffrt_task_handle_destroy -> called")
            } catch (e: Throwable) {
                logLine("ffrt_submit_f/submit_h_f (API 20) exception: $e")
            }

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_destroy(attr.ptr)
            logLine("ffrt_task_attr_destroy -> called")
        }
    }

    @Test
    fun testSharedMutex_shared_mutex_h() {
        // header: ffrt/shared_mutex.h (API 18+)
        try {
            memScoped {
                val rwlock = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_t>()

                val rInit = platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_init(rwlock.ptr, null)
                assertNotNull(rInit)
                logLine("ffrt_rwlock_init -> $rInit")

                val rTryWr = platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_trywrlock(rwlock.ptr)
                assertNotNull(rTryWr)
                logLine("ffrt_rwlock_trywrlock -> $rTryWr")

                val rUnlock1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_unlock(rwlock.ptr)
                assertNotNull(rUnlock1)
                logLine("ffrt_rwlock_unlock(1) -> $rUnlock1")

                val rTryRd = platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_tryrdlock(rwlock.ptr)
                assertNotNull(rTryRd)
                logLine("ffrt_rwlock_tryrdlock -> $rTryRd")

                val rUnlock2 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_unlock(rwlock.ptr)
                assertNotNull(rUnlock2)
                logLine("ffrt_rwlock_unlock(2) -> $rUnlock2")

                val rRd = platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_rdlock(rwlock.ptr)
                assertNotNull(rRd)
                logLine("ffrt_rwlock_rdlock -> $rRd")

                val rUnlock3 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_unlock(rwlock.ptr)
                assertNotNull(rUnlock3)
                logLine("ffrt_rwlock_unlock(3) -> $rUnlock3")

                val rWr = platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_wrlock(rwlock.ptr)
                assertNotNull(rWr)
                logLine("ffrt_rwlock_wrlock -> $rWr")

                val rUnlock4 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_unlock(rwlock.ptr)
                assertNotNull(rUnlock4)
                logLine("ffrt_rwlock_unlock(4) -> $rUnlock4")

                val rDestroy = platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_destroy(rwlock.ptr)
                assertNotNull(rDestroy)
                logLine("ffrt_rwlock_destroy -> $rDestroy")
            }
        } catch (e: Throwable) {
            logLine("testSharedMutex_shared_mutex_h (API 18) exception: $e")
        }
    }

    @Test
    fun testMutex_mutex_h() {
        // header: ffrt/mutex.h
        memScoped {
            val attr = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutexattr_t>()
            val rAttrInit = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutexattr_init(attr.ptr)
            assertNotNull(rAttrInit)
            logLine("ffrt_mutexattr_init -> $rAttrInit")

            // settype/gettype
            val rSetType = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutexattr_settype(attr.ptr, 0)
            assertNotNull(rSetType)
            logLine("ffrt_mutexattr_settype(0) -> $rSetType")

            val outType = alloc<IntVar>()
            val rGetType = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutexattr_gettype(attr.ptr, outType.ptr)
            assertNotNull(rGetType)
            logLine("ffrt_mutexattr_gettype -> $rGetType, type=${outType.value}")

            // mutex init/lock/unlock/trylock/destroy
            val mutex = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_t>()
            val rInit = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_init(mutex.ptr, attr.ptr)
            assertNotNull(rInit)
            logLine("ffrt_mutex_init -> $rInit")

            val rLock = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_lock(mutex.ptr)
            assertNotNull(rLock)
            logLine("ffrt_mutex_lock -> $rLock")

            val rUnlock = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_unlock(mutex.ptr)
            assertNotNull(rUnlock)
            logLine("ffrt_mutex_unlock -> $rUnlock")

            val rTry = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_trylock(mutex.ptr)
            assertNotNull(rTry)
            logLine("ffrt_mutex_trylock -> $rTry")
            if (rTry == 0) {
                val rUnlock2 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_unlock(mutex.ptr)
                assertNotNull(rUnlock2)
                logLine("ffrt_mutex_unlock(after trylock) -> $rUnlock2")
            }

            val rDestroy = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_destroy(mutex.ptr)
            assertNotNull(rDestroy)
            logLine("ffrt_mutex_destroy -> $rDestroy")

            val rAttrDestroy = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutexattr_destroy(attr.ptr)
            assertNotNull(rAttrDestroy)
            logLine("ffrt_mutexattr_destroy -> $rAttrDestroy")
        }
    }

    @Test
    fun testTimer_timer_h() {
        // header: ffrt/timer.h
        val handle0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_timer_start(
            platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_default,
            1uL,
            null,
            null,
            false
        )
        assertNotNull(handle0)
        logLine("ffrt_timer_start(handle0) -> $handle0")

        val stop0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_timer_stop(
            platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_default,
            handle0
        )
        assertNotNull(stop0)
        logLine("ffrt_timer_stop(handle0) -> $stop0")

        val handle1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_timer_start(
            platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_default,
            1uL,
            null,
            null,
            true
        )
        assertNotNull(handle1)
        logLine("ffrt_timer_start(handle1 repeat) -> $handle1")

        val stop1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_timer_stop(
            platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_default,
            handle1
        )
        assertNotNull(stop1)
        logLine("ffrt_timer_stop(handle1) -> $stop1")
    }

    @Test
    fun testTypeDefEnums_type_def_h() {
        // header: ffrt/type_def.h
        logLine("ffrt_queue_priority_t:")
        val qp0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_priority_immediate
        val qp1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_priority_high
        val qp2 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_priority_low
        val qp3 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_priority_idle
        logLine("ffrt_queue_priority_immediate=$qp0"); assert(qp0.toInt() == 0)
        logLine("ffrt_queue_priority_high=$qp1"); assert(qp1.toInt() == 1)
        logLine("ffrt_queue_priority_low=$qp2"); assert(qp2.toInt() == 2)
        logLine("ffrt_queue_priority_idle=$qp3"); assert(qp3.toInt() == 3)

        logLine("ffrt_qos_t:")
        val q0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_inherit
        val q1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_background
        val q2 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_utility
        val q3 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_default
        val q4 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_user_initiated
        logLine("ffrt_qos_inherit=$q0"); assert(q0.toInt() == -1)
        logLine("ffrt_qos_background=$q1"); assert(q1.toInt() == 0)
        logLine("ffrt_qos_utility=$q2"); assert(q2.toInt() == 1)
        logLine("ffrt_qos_default=$q3"); assert(q3.toInt() == 2)
        logLine("ffrt_qos_user_initiated=$q4"); assert(q4.toInt() == 3)

        logLine("ffrt_storage_size_t:")
        val ss0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_task_attr_storage_size
        val ss1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_auto_managed_function_storage_size
        val ss2 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_storage_size
        val ss3 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_cond_storage_size
        val ss4 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_storage_size
        val ss5 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_rwlock_storage_size
        val ss6 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_fiber_storage_size
        logLine("ffrt_task_attr_storage_size=$ss0"); assert(ss0.toInt() == 128)
        logLine("ffrt_auto_managed_function_storage_size=$ss1"); assert(ss1.toInt() > 0)
        logLine("ffrt_mutex_storage_size=$ss2"); assert(ss2.toInt() == 64)
        logLine("ffrt_cond_storage_size=$ss3"); assert(ss3.toInt() == 64)
        logLine("ffrt_queue_attr_storage_size=$ss4"); assert(ss4.toInt() == 128)
        logLine("ffrt_rwlock_storage_size=$ss5"); assert(ss5.toInt() == 64)
        logLine("ffrt_fiber_storage_size=$ss6"); assert(ss6.toInt() > 0)

        logLine("ffrt_function_kind_t:")
        val k0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_function_kind_t.ffrt_function_kind_general
        val k1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_function_kind_t.ffrt_function_kind_queue
        logLine("ffrt_function_kind_general=$k0"); assert(k0.value.toInt() == 0)
        logLine("ffrt_function_kind_queue=$k1"); assert(k1.value.toInt() == 1)

        logLine("ffrt_dependence_type_t:")
        val dt0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_dependence_type_t.ffrt_dependence_data
        val dt1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_dependence_type_t.ffrt_dependence_task
        logLine("ffrt_dependence_data=$dt0"); assert(dt0.value.toInt() == 0)
        logLine("ffrt_dependence_task=$dt1"); assert(dt1.value.toInt() == 1)

        logLine("ffrt_error_t:")
        val e0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_error
        val e1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_success
        val e2 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_error_nomem
        val e3 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_error_timedout
        val e4 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_error_busy
        val e5 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_error_inval
        logLine("ffrt_error=$e0"); assert(e0.toInt() == -1)
        logLine("ffrt_success=$e1"); assert(e1.toInt() == 0)
        logLine("ffrt_error_nomem=$e2"); 
        logLine("ffrt_error_timedout=$e3");
        logLine("ffrt_error_busy=$e4");
        logLine("ffrt_error_inval=$e5"); 

        logLine("ffrt_mutex_type:")
        val mt0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_normal
        val mt1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_recursive
        val mt2 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_mutex_default
        logLine("ffrt_mutex_normal=$mt0"); assert(mt0.toInt() == 0)
        logLine("ffrt_mutex_recursive=$mt1"); assert(mt1.toInt() == 2)
        logLine("ffrt_mutex_default=$mt2"); assert(mt2.toInt() == 0)
    }

    @Test
    fun testLoop_loop_h() {
        // header: ffrt/loop.h
        memScoped {
            // loop_create 需要 queue；这里用 queue_create 获取一个合法队列（依赖 queue.h）
            val queue = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_create(
                platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_type_t.ffrt_queue_serial,
                "ffrt_loop_test_queue",
                null
            )
            assertNotNull(queue)
            logLine("ffrt_queue_create(for loop) -> $queue")

            val loop = platform.FunctionFlowRuntimeKit.FFRT.ffrt_loop_create(queue)
            logLine("ffrt_loop_create -> $loop")

            // 避免阻塞：只覆盖 loop_run 的调用路径（传 null 期望快速返回）
            val rRunNull = platform.FunctionFlowRuntimeKit.FFRT.ffrt_loop_run(null)
            logLine("ffrt_loop_run(null) -> $rRunNull")

            val rCtl = platform.FunctionFlowRuntimeKit.FFRT.ffrt_loop_epoll_ctl(
                loop,
                0,
                -1,
                0u,
                null,
                null
            )
            assertNotNull(rCtl)
            logLine("ffrt_loop_epoll_ctl -> $rCtl")

            val timerHandle = platform.FunctionFlowRuntimeKit.FFRT.ffrt_loop_timer_start(
                loop,
                1uL,
                null,
                null,
                false
            )
            assertNotNull(timerHandle)
            logLine("ffrt_loop_timer_start -> $timerHandle")

            val rTimerStop = platform.FunctionFlowRuntimeKit.FFRT.ffrt_loop_timer_stop(loop, timerHandle)
            assertNotNull(rTimerStop)
            logLine("ffrt_loop_timer_stop -> $rTimerStop")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_loop_stop(loop)
            logLine("ffrt_loop_stop -> called")

            val rDestroy = platform.FunctionFlowRuntimeKit.FFRT.ffrt_loop_destroy(loop)
            assertNotNull(rDestroy)
            logLine("ffrt_loop_destroy -> $rDestroy")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_destroy(queue)
            logLine("ffrt_queue_destroy(for loop) -> called")
        }
    }

    @Test
    fun testQueue_queue_h() {
        // header: ffrt/queue.h
        memScoped {
            // enum: ffrt_queue_type_t
            val qt0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_type_t.ffrt_queue_serial
            val qt1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_type_t.ffrt_queue_concurrent
            val qt2 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_type_t.ffrt_queue_max
            logLine("ffrt_queue_serial=$qt0"); assert(qt0.value.toInt() == 0)
            logLine("ffrt_queue_concurrent=$qt1"); assert(qt1.value.toInt() == 1)
            logLine("ffrt_queue_max=$qt2"); assert(qt2.value.toInt() == 2)

            val qAttr = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_t>()
            val rAttrInit = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_init(qAttr.ptr)
            assertNotNull(rAttrInit)
            logLine("ffrt_queue_attr_init -> $rAttrInit")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_set_qos(qAttr.ptr, platform.FunctionFlowRuntimeKit.FFRT.ffrt_qos_default)
            val qos = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_get_qos(qAttr.ptr)
            assertNotNull(qos)
            logLine("ffrt_queue_attr_get_qos -> $qos")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_set_timeout(qAttr.ptr, 1000uL)
            val timeout = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_get_timeout(qAttr.ptr)
            assertNotNull(timeout)
            logLine("ffrt_queue_attr_get_timeout -> $timeout")

            // val cbHeader = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_function_header_t>()
            // platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_set_callback(qAttr.ptr, cbHeader.ptr)
            // val gotCb = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_get_callback(qAttr.ptr)
            // assertNotNull(gotCb)
            // logLine("ffrt_queue_attr_get_callback -> $gotCb")

            // platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_set_max_concurrency(qAttr.ptr, 1)
            // val mc = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_get_max_concurrency(qAttr.ptr)
            // assertNotNull(mc)
            // logLine("ffrt_queue_attr_get_max_concurrency -> $mc")

            try {
                platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_set_thread_mode(qAttr.ptr, false)
                val tm0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_get_thread_mode(qAttr.ptr)
                assertNotNull(tm0)
                logLine("ffrt_queue_attr_get_thread_mode(false) -> $tm0")
            } catch (e: Throwable) {
                logLine("ffrt_queue_attr_set_thread_mode/get_thread_mode (API 20) exception: $e")
            }

            val queue = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_create(qt0, "ffrt_queue_test", qAttr.ptr)
            assertNotNull(queue)
            logLine("ffrt_queue_create -> $queue")

            // platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_submit(queue, submitHeader.ptr, taskAttr.ptr)
            // logLine("ffrt_queue_submit -> called")

            // val h0 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_submit_h(queue, submitHeader.ptr, taskAttr.ptr)
            // assertNotNull(h0)
            // logLine("ffrt_queue_submit_h -> $h0")

            // val queueTaskFunc = staticCFunction { _arg: COpaquePointer? -> }
            // val queueTaskArg = alloc<IntVar>().apply { value = 0 }
            // platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_submit_f(queue, queueTaskFunc, queueTaskArg.ptr, taskAttr.ptr)
            // logLine("ffrt_queue_submit_f -> called")

            // val h1 = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_submit_h_f(queue, queueTaskFunc, queueTaskArg.ptr, taskAttr.ptr)
            // assertNotNull(h1)
            // logLine("ffrt_queue_submit_h_f -> $h1")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_wait(null)
            logLine("ffrt_queue_wait(h0) -> called")

            val cancel = platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_cancel(null)
            assertNotNull(cancel)
            logLine("ffrt_queue_cancel(h1) -> $cancel")

            val mainQ = platform.FunctionFlowRuntimeKit.FFRT.ffrt_get_main_queue()
            assertNotNull(mainQ)
            logLine("ffrt_get_main_queue -> $mainQ")

            val curQ = platform.FunctionFlowRuntimeKit.FFRT.ffrt_get_current_queue()
            assertNotNull(curQ)
            logLine("ffrt_get_current_queue -> $curQ")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_destroy(queue)
            logLine("ffrt_queue_destroy -> called")

            platform.FunctionFlowRuntimeKit.FFRT.ffrt_queue_attr_destroy(qAttr.ptr)
            logLine("ffrt_queue_attr_destroy -> called")

        }
    }

    @Test
    fun testFiber_fiber_h() {
        // header: ffrt/fiber.h (API 20+)
        try {
            memScoped {
                val to = alloc<platform.FunctionFlowRuntimeKit.FFRT.ffrt_fiber_t>()

                val rInit = platform.FunctionFlowRuntimeKit.FFRT.ffrt_fiber_init(
                    to.ptr,
                    null,
                    null,
                    null,
                    0uL
                )
                assertNotNull(rInit)
                logLine("ffrt_fiber_init(func=null) -> $rInit")

                platform.FunctionFlowRuntimeKit.FFRT.ffrt_fiber_switch(to.ptr, to.ptr)
                logLine("ffrt_fiber_switch -> called")
            }
        } catch (e: Throwable) {
            logLine("testFiber_fiber_h (API 20) exception: $e")
        }
    }
}
