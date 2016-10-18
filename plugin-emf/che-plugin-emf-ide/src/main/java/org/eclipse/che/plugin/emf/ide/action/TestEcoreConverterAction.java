package org.eclipse.che.plugin.emf.ide.action;

import com.google.inject.Inject;
import org.eclipse.che.api.promises.client.Operation;
import org.eclipse.che.api.promises.client.OperationException;
import org.eclipse.che.api.promises.client.PromiseError;
import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionEvent;
import org.eclipse.che.ide.api.notification.NotificationManager;
import org.eclipse.che.ide.api.notification.StatusNotification;
import org.eclipse.che.plugin.emf.shared.Constants;

/**
 * @author Mathias Hansen <mhansen@eclipsesource.com>
 */
public class TestEcoreConverterAction extends Action {

    private final NotificationManager notificationManager;
    private final EcoreConverterClient serviceClient;

    public final static String ACTION_ID = "testEcoreConverterAction";

    /**
     * Constructor.
     *
     * @param notificationManager the notification manager
     * @param serviceClient the client that is used to create requests
     */
    @Inject
    public TestEcoreConverterAction(final NotificationManager notificationManager,
                                    final EcoreConverterClient serviceClient) {
        super("Test Action", "Test Action Description");
        this.notificationManager = notificationManager;
        this.serviceClient = serviceClient;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // This calls the service in the workspace.
        // This method is in our org.eclipse.che.plugin.serverservice.ide.MyServiceClient class
        // This is a Promise, so the .then() method is invoked after the response is made
        serviceClient.convertJsonToXmi(Constants.DUMMY_DATA).then(new Operation<String>() {
            @Override
            public void apply(String response) throws OperationException {
                // This passes the response String to the notification manager.
                notificationManager.notify(response, StatusNotification.Status.SUCCESS, StatusNotification.DisplayMode.FLOAT_MODE);
            }
        }).catchError(new Operation<PromiseError>() {
            @Override
            public void apply(PromiseError error) throws OperationException {
                notificationManager.notify("Fail", StatusNotification.Status.FAIL, StatusNotification.DisplayMode.FLOAT_MODE);
            }
        });
    }
}
